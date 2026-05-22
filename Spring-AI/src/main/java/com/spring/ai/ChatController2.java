package com.spring.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat2")
public class ChatController2 {

    private final ChatClient chatClient;

    public ChatController2(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return chatClient
                .prompt(request.message())
                .call()
                .content();
    }

    @GetMapping
    public String chat() {
        return "sdfdsfdsfdsf";
    }
    public record ChatRequest(String message) {}
}