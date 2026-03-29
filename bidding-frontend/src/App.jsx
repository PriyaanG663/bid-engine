import React, { useState, useEffect, useRef, useCallback } from 'react';
import { Box, LogOut, Trophy, ArrowUpCircle, Activity, ShieldCheck, PlusCircle } from 'lucide-react';

const PRODUCTS = [
  { id: 'S-701', name: 'Tokyo Express', startPrice: 1200 },
  { id: 'S-402', name: 'Berlin Cargo', startPrice: 5000 },
  { id: 'S-109', name: 'Dubai Air', startPrice: 8500 },
  { id: 'S-882', name: 'NYC Freight', startPrice: 2100 },
  { id: 'S-551', name: 'Singapore Port', startPrice: 3400 },
];

export default function App() {
  const [user, setUser] = useState(null);
  const [balance, setBalance] = useState(0);
  const [prices, setPrices] = useState({});
  const [leaders, setLeaders] = useState({});
  const [bidInputs, setBidInputs] = useState({});
  const [logs, setLogs] = useState([]);
  const [form, setForm] = useState({ username: '', password: '' });
  const [error, setError] = useState("");
  const socket = useRef(null);

  const connect = useCallback(() => {
    socket.current = new WebSocket('ws://localhost:8080/ws');
    socket.current.onmessage = (e) => {
      const data = JSON.parse(e.data);
      if (data.type === "AUTH_RES" && data.success) {
        setUser(data.username);
        setBalance(data.balance);
      } else if (data.type === "SYNC_STATE") {
        setPrices(data.prices);
        setLeaders(data.leaders);
      } else if (data.type === "BALANCE_UPDATE" && data.username === user) {
        setBalance(data.balance);
        if (data.message) addLog(data.message);
      } else if (data.type === "NEW_BID") {
        setPrices(p => ({ ...p, [data.productID]: data.currentPrice }));
        setLeaders(l => ({ ...l, [data.productID]: data.username }));
        addLog(data.message);
      } else if (data.type === "ERROR") {
        setError(data.message);
        setTimeout(() => setError(""), 4000);
      }
    };
  }, [user]);

  const addLog = (msg) => setLogs(prev => [{ msg, time: new Date().toLocaleTimeString(), id: Date.now() }, ...prev.slice(0, 10)]);

  useEffect(() => { connect(); return () => socket.current?.close(); }, [connect]);

  const send = (type, extras = {}) => socket.current?.send(JSON.stringify({ type, ...form, username: user || form.username, ...extras }));

  if (!user) return (
    <div className="min-h-screen flex items-center justify-center bg-[#020617] text-white p-6">
      <div className="w-full max-w-md bg-[#0f172a] p-10 rounded-[2.5rem] border border-slate-800">
        <h1 className="text-3xl font-black text-center mb-8 uppercase tracking-widest">RouteMaster</h1>
        <input className="w-full bg-[#020617] border border-slate-800 p-4 rounded-xl mb-4" placeholder="Operator ID" onChange={e => setForm({...form, username: e.target.value})} />
        <input className="w-full bg-[#020617] border border-slate-800 p-4 rounded-xl mb-6" type="password" placeholder="Passkey" onChange={e => setForm({...form, password: e.target.value})} />
        <button onClick={() => send("LOGIN")} className="w-full bg-blue-600 py-4 rounded-xl font-bold uppercase">Enter Terminal</button>
        <button onClick={() => send("SIGNUP")} className="w-full text-slate-500 mt-4 text-xs font-bold uppercase">New Account</button>
      </div>
    </div>
  );

  return (
    <div className="max-w-[1400px] mx-auto p-8 bg-[#020617] min-h-screen text-slate-200">
      <nav className="flex justify-between items-center mb-10 bg-[#0f172a] p-6 rounded-3xl border border-slate-800">
        <h1 className="text-xl font-black uppercase tracking-tighter">AUCTION-{user} <span className="text-blue-500">Terminal</span></h1>
        <div className="bg-[#020617] px-6 py-2 rounded-xl border border-slate-800">
          <span className="text-[10px] block font-bold text-slate-500 uppercase">Wallet</span>
          <span className="text-emerald-400 font-mono font-bold text-xl">${balance.toLocaleString()}</span>
        </div>
      </nav>

      <div className="grid lg:grid-cols-12 gap-8">
        <div className="lg:col-span-8 grid md:grid-cols-2 gap-6">
          {PRODUCTS.map(p => {
            const currentPrice = prices[p.id] || p.startPrice;
            const inputVal = bidInputs[p.id] || 0;
            // CHECK: Wallet must have enough for the bid AND bid must be higher than current price
            const isAffordable = balance >= inputVal;
            const isHigher = inputVal > currentPrice;

            return (
              <div key={p.id} className="bg-[#0f172a] p-8 rounded-[2rem] border border-slate-800 flex flex-col">
                <div className="flex justify-between mb-4">
                  <span className="text-blue-500 font-bold text-xs">{p.id}</span>
                  <span className="text-3xl font-black font-mono">${currentPrice.toLocaleString()}</span>
                </div>
                <h2 className="text-xl font-bold mb-4">{p.name}</h2>
                <p className="text-xs text-slate-500 mb-6 flex gap-2"><Trophy size={14}/> {leaders[p.id] || 'No Bids'}</p>
                
                <div className="mt-auto">
                  <div className="flex gap-2">
                    <input 
                      type="number" 
                      className={`flex-1 bg-[#020617] border p-3 rounded-lg font-mono text-sm ${!isAffordable ? 'border-red-500' : 'border-slate-800'}`} 
                      placeholder="Amount" 
                      onChange={e => setBidInputs({...bidInputs, [p.id]: parseFloat(e.target.value) || 0})} 
                    />
                    <button 
                      disabled={!isAffordable || !isHigher}
                      onClick={() => send("BID", { productID: p.id, amount: inputVal })} 
                      className={`px-4 rounded-lg transition-all ${(!isAffordable || !isHigher) ? 'bg-slate-800 text-slate-500 opacity-50' : 'bg-blue-600 text-white'}`}
                    >
                      <ArrowUpCircle/>
                    </button>
                  </div>
                  {!isAffordable && <p className="text-[10px] text-red-500 mt-2 font-bold uppercase">Insufficient Wallet Funds</p>}
                </div>
              </div>
            )
          })}
          
          {/* DEPOSIT BUTTON SECTION */}
          <div className="md:col-span-2 bg-gradient-to-r from-emerald-500/10 to-transparent p-8 rounded-[2rem] border border-emerald-500/20 flex items-center justify-between">
            <div>
              <p className="text-emerald-400 font-black uppercase text-xs tracking-widest">Wallet Management</p>
              <p className="text-slate-500 text-[10px] font-bold uppercase">Request $1,000.00 Liquidity Injection</p>
            </div>
            <button 
              onClick={() => send("ADD_FUNDS", { amount: 1000 })}
              className="flex items-center gap-2 bg-emerald-500 hover:bg-emerald-400 text-slate-900 px-6 py-3 rounded-xl font-black text-xs uppercase transition-all"
            >
              <PlusCircle size={16}/> Deposit $1K
            </button>
          </div>
        </div>

        <div className="lg:col-span-4 bg-[#0f172a] rounded-[2rem] border border-slate-800 p-6">
          <h3 className="text-xs font-black uppercase mb-6 flex items-center gap-2"><Activity size={16} className="text-blue-500"/> Transmission Log {error && <span className="text-red-500 ml-auto">! {error}</span>}</h3>
          <div className="space-y-4">
            {logs.map(log => (
              <div key={log.id} className="bg-[#020617] p-4 rounded-xl border border-slate-800/50">
                <p className="text-xs font-bold text-slate-300">{log.msg}</p>
                <span className="text-[10px] text-slate-600">{log.time}</span>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}