package com.hyunganom.salesforcemiddleware.core.service;

import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTRequest;
import com.hyunganom.salesforcemiddleware.core.ports.in.ChatGPTUseCase;
import com.hyunganom.salesforcemiddleware.core.ports.out.ChatGPTAPIClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * ChatGPT와 상호 작용하는 서비스 클래스.
 * 주어진 프롬프트에 대해 ChatGPT 응답을 처리.
 */
@Service
@RequiredArgsConstructor
public class ChatGPTService implements ChatGPTUseCase {

    private final Environment environment;
    private final ChatGPTAPIClient chatGPTAPIClient;

    /**
     * 주어진 프롬프트로 ChatGPT와 대화하고 응답을 반환.
     *
     * @param prompt 사용자 입력 프롬프트
     * @return ChatGPT의 응답
     */
    @Override
    public String chat(String prompt) {
        String model = environment.getProperty("openai.model");
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        return chatGPTAPIClient.chat(request).getChoices().get(0).getMessage().getContent();

//        String model = environment.getProperty("openai.model");
//        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
//        ChatGPTResponse response = chatGPTAPIClient.chat(request);
//        Choice choice = response.getChoices().get(0);
//        Message message = choice.getMessage();
//        return message.getContent();
    }
}