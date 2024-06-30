package com.hyunganom.salesforcemiddleware.core.domain.chatgpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatGPTResponse {
    private String id;
    private String object; // 추가된 필드
    private List<Choice> choices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true) // 이 어노테이션 추가
    public static class Choice {
        private int index;
        private GPTMessage message;
        private Object logprobs; // 추가된 필드
    }
}