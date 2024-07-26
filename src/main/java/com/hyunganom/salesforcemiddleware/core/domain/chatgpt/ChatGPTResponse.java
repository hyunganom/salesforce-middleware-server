package com.hyunganom.salesforcemiddleware.core.domain.chatgpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ChatGPT API 응답을 위한 클래스.
 * 응답 ID, 객체 유형, 선택 리스트를 포함.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatGPTResponse {
    private String id;
    private String object;
    private List<Choice> choices;

    /**
     * ChatGPT 응답의 선택 항목을 나타내는 클래스.
     * 인덱스, 메시지, 로그 확률을 포함.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private int index;
        private GPTMessage message;
        private Object logprobs;
    }
}