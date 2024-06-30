package com.hyunganom.salesforcemiddleware.core.domain.chatgpt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<GPTMessage> messages; // 필드 이름을 변경

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new GPTMessage("user", prompt)); // 필드 이름 변경 반영
    }
}