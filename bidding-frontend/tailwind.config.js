@import "tailwindcss";

@theme {
  --color-background: #0f172a;
  --color-card: #1e293b;
}

@layer base {
  body {
    @apply bg-background text-slate-200 antialiased;
    margin: 0;
    font-family: 'Inter', system-ui, -apple-system, sans-serif;
  }
}

/* Smooth animations for the live feed */
@keyframes slideIn {
  from { opacity: 0; transform: translateX(20px); }
  to { opacity: 1; transform: translateX(0); }
}

.animate-feed {
  animation: slideIn 0.3s ease-out forwards;
}