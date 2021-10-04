Deadlines allow gRPC clients to specify how long they are willing to wait for an RPC to complete
before the RPC is terminated with the error DEADLINE_EXCEEDED



The gRPC documentation recommends you set a deadline for all client RPC calls



Setting the deadline is up to you: how long do yu feel your API should have to complete ?


The server should check if the deadline has exceeded and cancel the work it is doing


This blog describes deadline in depth:
https://grpc.io/blog/deadlines


Note: Deadlines are propagated across if gRPC calls are chained
(A-->B-->C) (deadline for A is passed to B and then passed to C)
--------------------------------------------------------------
https://github.com/grpc/grpc-java
https://grpc.io/docs/guides/auth.html