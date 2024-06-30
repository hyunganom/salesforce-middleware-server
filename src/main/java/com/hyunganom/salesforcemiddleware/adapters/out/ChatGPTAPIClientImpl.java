package com.hyunganom.salesforcemiddleware.adapters.out;

import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTRequest;
import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTResponse;
import com.hyunganom.salesforcemiddleware.core.ports.out.ChatGPTAPIClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ChatGPTAPIClientImpl implements ChatGPTAPIClient {

    private final RestTemplate restTemplate;
    private final Environment environment;

    @Override
    public ChatGPTResponse chat(ChatGPTRequest request) {
        String apiURL = environment.getProperty("openai.api.url");
        return restTemplate.postForObject(apiURL, request, ChatGPTResponse.class);
    }
}
