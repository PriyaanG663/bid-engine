package com.auction.identity;

import com.auction.identity.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@GrpcService
public class IdentityServiceImpl extends IdentityServiceGrpc.IdentityServiceImplBase {

    @Autowired
    private UserRepository userRepository; // Assuming you have a standard JpaRepository

    @Override
    public void signup(SignupRequest request, StreamObserver<AuthResponse> responseObserver) {
        if (userRepository.existsByUsername(request.getUsername())) {
            responseObserver.onNext(AuthResponse.newBuilder()
                .setSuccess(false).setMessage("User already exists").build());
        } else {
            UserAccount newUser = new UserAccount(request.getUsername(), request.getPassword(), request.getInitialBalance());
            userRepository.save(newUser);
            responseObserver.onNext(AuthResponse.newBuilder()
                .setSuccess(true).setBalance(newUser.getBalance()).setUsername(newUser.getUsername()).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void login(LoginRequest request, StreamObserver<AuthResponse> responseObserver) {
        Optional<UserAccount> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            responseObserver.onNext(AuthResponse.newBuilder()
                .setSuccess(true).setBalance(user.get().getBalance()).setUsername(user.get().getUsername()).build());
        } else {
            responseObserver.onNext(AuthResponse.newBuilder().setSuccess(false).setMessage("Invalid credentials").build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void updateBalance(UpdateBalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        Optional<UserAccount> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            UserAccount account = user.get();
            account.setBalance(account.getBalance() + request.getAmount());
            userRepository.save(account);
            responseObserver.onNext(BalanceResponse.newBuilder().setBalance(account.getBalance()).setExists(true).build());
        } else {
            responseObserver.onNext(BalanceResponse.newBuilder().setExists(false).build());
        }
        responseObserver.onCompleted();
    }
}