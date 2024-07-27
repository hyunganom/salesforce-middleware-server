package com.hyunganom.salesforcemiddleware.core.ports.out;

import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTRequest;
import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTResponse;

/**
 * ChatGPT API 클라이언트를 나타내는 인터페이스.
 * ChatGPT와의 상호 작용을 처리.
 */
public interface ChatGPTAPIClient {
    /**
     * ChatGPT와 상호 작용하여 응답을 받음.
     *
     * @param request ChatGPT 요청 객체
     * @return ChatGPT 응답 객체
     */
    ChatGPTResponse chat(ChatGPTRequest request);
}