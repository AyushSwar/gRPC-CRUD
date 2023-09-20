package com.example.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class ServerStarter {
    @Inject
    public MovieServiceImpl movieService;

    public void startService() throws IOException, InterruptedException{

        Server service = ServerBuilder.forPort(8081).addService(movieService).build().start();

        // Shutdown Hook:
        Runtime.getRuntime().addShutdownHook(new Thread(service::shutdownNow));
        System.out.println("Started Listening for rpc calls on 8081..");
        service.awaitTermination();

    }
}
