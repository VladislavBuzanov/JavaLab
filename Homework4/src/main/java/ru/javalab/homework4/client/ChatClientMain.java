package ru.javalab.homework4.client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatClientMain {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        Scanner sc = new Scanner(System.in);
        ChatClient chatClient = new ChatClient();
        chatClient.startConnection(args.serverIp, args.port);

        while (true) {
            String message = sc.nextLine();
            chatClient.sendMessage(message);
        }
    }

    @Parameters(separators = "=")
    public static class Args {
        @Parameter
        public List<String> parameters = new ArrayList<>();

        @Parameter(names = {"--server-port"})
        private Integer port;

        @Parameter(names = "--server-ip")
        private String serverIp;

    }
}
