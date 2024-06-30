// core/service/ChatGPTService.java
package com.hyunganom.salesforcemiddleware.core.service;

import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTRequest;
import com.hyunganom.salesforcemiddleware.core.ports.in.ChatGPTUseCase;
import com.hyunganom.salesforcemiddleware.core.ports.out.ChatGPTAPIClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatGPTService implements ChatGPTUseCase {

    private final Environment environment;
    private final ChatGPTAPIClient chatGPTAPIClient;

    @Override
    public String chat(String prompt) {
        String model = environment.getProperty("openai.model");
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        return chatGPTAPIClient.chat(request).getChoices().get(0).getMessage().getContent();
    }
}