package com.auction.identity.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: identity.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class IdentityServiceGrpc {

  private IdentityServiceGrpc() {}

  public static final String SERVICE_NAME = "identity.IdentityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.auction.identity.grpc.SignupRequest,
      com.auction.identity.grpc.AuthResponse> getSignupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Signup",
      requestType = com.auction.identity.grpc.SignupRequest.class,
      responseType = com.auction.identity.grpc.AuthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.auction.identity.grpc.SignupRequest,
      com.auction.identity.grpc.AuthResponse> getSignupMethod() {
    io.grpc.MethodDescriptor<com.auction.identity.grpc.SignupRequest, com.auction.identity.grpc.AuthResponse> getSignupMethod;
    if ((getSignupMethod = IdentityServiceGrpc.getSignupMethod) == null) {
      synchronized (IdentityServiceGrpc.class) {
        if ((getSignupMethod = IdentityServiceGrpc.getSignupMethod) == null) {
          IdentityServiceGrpc.getSignupMethod = getSignupMethod =
              io.grpc.MethodDescriptor.<com.auction.identity.grpc.SignupRequest, com.auction.identity.grpc.AuthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Signup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.SignupRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.AuthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IdentityServiceMethodDescriptorSupplier("Signup"))
              .build();
        }
      }
    }
    return getSignupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.auction.identity.grpc.LoginRequest,
      com.auction.identity.grpc.AuthResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = com.auction.identity.grpc.LoginRequest.class,
      responseType = com.auction.identity.grpc.AuthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.auction.identity.grpc.LoginRequest,
      com.auction.identity.grpc.AuthResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<com.auction.identity.grpc.LoginRequest, com.auction.identity.grpc.AuthResponse> getLoginMethod;
    if ((getLoginMethod = IdentityServiceGrpc.getLoginMethod) == null) {
      synchronized (IdentityServiceGrpc.class) {
        if ((getLoginMethod = IdentityServiceGrpc.getLoginMethod) == null) {
          IdentityServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.auction.identity.grpc.LoginRequest, com.auction.identity.grpc.AuthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.AuthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IdentityServiceMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.auction.identity.grpc.BalanceRequest,
      com.auction.identity.grpc.BalanceResponse> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalance",
      requestType = com.auction.identity.grpc.BalanceRequest.class,
      responseType = com.auction.identity.grpc.BalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.auction.identity.grpc.BalanceRequest,
      com.auction.identity.grpc.BalanceResponse> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<com.auction.identity.grpc.BalanceRequest, com.auction.identity.grpc.BalanceResponse> getGetBalanceMethod;
    if ((getGetBalanceMethod = IdentityServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (IdentityServiceGrpc.class) {
        if ((getGetBalanceMethod = IdentityServiceGrpc.getGetBalanceMethod) == null) {
          IdentityServiceGrpc.getGetBalanceMethod = getGetBalanceMethod =
              io.grpc.MethodDescriptor.<com.auction.identity.grpc.BalanceRequest, com.auction.identity.grpc.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.BalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IdentityServiceMethodDescriptorSupplier("GetBalance"))
              .build();
        }
      }
    }
    return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.auction.identity.grpc.UpdateBalanceRequest,
      com.auction.identity.grpc.BalanceResponse> getUpdateBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateBalance",
      requestType = com.auction.identity.grpc.UpdateBalanceRequest.class,
      responseType = com.auction.identity.grpc.BalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.auction.identity.grpc.UpdateBalanceRequest,
      com.auction.identity.grpc.BalanceResponse> getUpdateBalanceMethod() {
    io.grpc.MethodDescriptor<com.auction.identity.grpc.UpdateBalanceRequest, com.auction.identity.grpc.BalanceResponse> getUpdateBalanceMethod;
    if ((getUpdateBalanceMethod = IdentityServiceGrpc.getUpdateBalanceMethod) == null) {
      synchronized (IdentityServiceGrpc.class) {
        if ((getUpdateBalanceMethod = IdentityServiceGrpc.getUpdateBalanceMethod) == null) {
          IdentityServiceGrpc.getUpdateBalanceMethod = getUpdateBalanceMethod =
              io.grpc.MethodDescriptor.<com.auction.identity.grpc.UpdateBalanceRequest, com.auction.identity.grpc.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.UpdateBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.auction.identity.grpc.BalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IdentityServiceMethodDescriptorSupplier("UpdateBalance"))
              .build();
        }
      }
    }
    return getUpdateBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IdentityServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IdentityServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IdentityServiceStub>() {
        @java.lang.Override
        public IdentityServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IdentityServiceStub(channel, callOptions);
        }
      };
    return IdentityServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IdentityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IdentityServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IdentityServiceBlockingStub>() {
        @java.lang.Override
        public IdentityServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IdentityServiceBlockingStub(channel, callOptions);
        }
      };
    return IdentityServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IdentityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IdentityServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IdentityServiceFutureStub>() {
        @java.lang.Override
        public IdentityServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IdentityServiceFutureStub(channel, callOptions);
        }
      };
    return IdentityServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class IdentityServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void signup(com.auction.identity.grpc.SignupRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.AuthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSignupMethod(), responseObserver);
    }

    /**
     */
    public void login(com.auction.identity.grpc.LoginRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.AuthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void getBalance(com.auction.identity.grpc.BalanceRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.BalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void updateBalance(com.auction.identity.grpc.UpdateBalanceRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.BalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSignupMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.auction.identity.grpc.SignupRequest,
                com.auction.identity.grpc.AuthResponse>(
                  this, METHODID_SIGNUP)))
          .addMethod(
            getLoginMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.auction.identity.grpc.LoginRequest,
                com.auction.identity.grpc.AuthResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getGetBalanceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.auction.identity.grpc.BalanceRequest,
                com.auction.identity.grpc.BalanceResponse>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getUpdateBalanceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.auction.identity.grpc.UpdateBalanceRequest,
                com.auction.identity.grpc.BalanceResponse>(
                  this, METHODID_UPDATE_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class IdentityServiceStub extends io.grpc.stub.AbstractAsyncStub<IdentityServiceStub> {
    private IdentityServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IdentityServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IdentityServiceStub(channel, callOptions);
    }

    /**
     */
    public void signup(com.auction.identity.grpc.SignupRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.AuthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSignupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void login(com.auction.identity.grpc.LoginRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.AuthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBalance(com.auction.identity.grpc.BalanceRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.BalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateBalance(com.auction.identity.grpc.UpdateBalanceRequest request,
        io.grpc.stub.StreamObserver<com.auction.identity.grpc.BalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class IdentityServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<IdentityServiceBlockingStub> {
    private IdentityServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IdentityServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IdentityServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.auction.identity.grpc.AuthResponse signup(com.auction.identity.grpc.SignupRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSignupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.auction.identity.grpc.AuthResponse login(com.auction.identity.grpc.LoginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.auction.identity.grpc.BalanceResponse getBalance(com.auction.identity.grpc.BalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.auction.identity.grpc.BalanceResponse updateBalance(com.auction.identity.grpc.UpdateBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class IdentityServiceFutureStub extends io.grpc.stub.AbstractFutureStub<IdentityServiceFutureStub> {
    private IdentityServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IdentityServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IdentityServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.auction.identity.grpc.AuthResponse> signup(
        com.auction.identity.grpc.SignupRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSignupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.auction.identity.grpc.AuthResponse> login(
        com.auction.identity.grpc.LoginRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.auction.identity.grpc.BalanceResponse> getBalance(
        com.auction.identity.grpc.BalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.auction.identity.grpc.BalanceResponse> updateBalance(
        com.auction.identity.grpc.UpdateBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SIGNUP = 0;
  private static final int METHODID_LOGIN = 1;
  private static final int METHODID_GET_BALANCE = 2;
  private static final int METHODID_UPDATE_BALANCE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final IdentityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(IdentityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SIGNUP:
          serviceImpl.signup((com.auction.identity.grpc.SignupRequest) request,
              (io.grpc.stub.StreamObserver<com.auction.identity.grpc.AuthResponse>) responseObserver);
          break;
        case METHODID_LOGIN:
          serviceImpl.login((com.auction.identity.grpc.LoginRequest) request,
              (io.grpc.stub.StreamObserver<com.auction.identity.grpc.AuthResponse>) responseObserver);
          break;
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((com.auction.identity.grpc.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.auction.identity.grpc.BalanceResponse>) responseObserver);
          break;
        case METHODID_UPDATE_BALANCE:
          serviceImpl.updateBalance((com.auction.identity.grpc.UpdateBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.auction.identity.grpc.BalanceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class IdentityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IdentityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.auction.identity.grpc.IdentityProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IdentityService");
    }
  }

  private static final class IdentityServiceFileDescriptorSupplier
      extends IdentityServiceBaseDescriptorSupplier {
    IdentityServiceFileDescriptorSupplier() {}
  }

  private static final class IdentityServiceMethodDescriptorSupplier
      extends IdentityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    IdentityServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IdentityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IdentityServiceFileDescriptorSupplier())
              .addMethod(getSignupMethod())
              .addMethod(getLoginMethod())
              .addMethod(getGetBalanceMethod())
              .addMethod(getUpdateBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
