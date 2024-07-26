package com.hyunganom.salesforcemiddleware.core.domain.chatgpt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ChatGPT API 요청을 위한 클래스.
 * 모델 유형과 메시지 리스트를 포함.
 */
@Data
public class ChatGPTRequest {
    private String model;
    private List<GPTMessage> messages; // 필드 이름을 변경

    /**
     * 모델과 프롬프트를 사용해 ChatGPTRequest 생성.
     * 초기 메시지를 리스트에 추가.
     *
     * @param model  ChatGPT 모델 유형
     * @param prompt 초기 사용자 메시지
     */
    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new GPTMessage("user", prompt)); // 필드 이름 변경 반영
    }
}