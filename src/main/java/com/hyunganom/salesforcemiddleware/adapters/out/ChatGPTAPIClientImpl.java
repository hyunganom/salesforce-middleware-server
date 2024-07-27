package com.hyunganom.salesforcemiddleware.adapters.out;

import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTRequest;
import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTResponse;
import com.hyunganom.salesforcemiddleware.core.ports.out.ChatGPTAPIClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatGPTAPIClientImpl implements ChatGPTAPIClient {

    private final RestTemplate restTemplate;
    private final Environment environment;

    @Override
    public ChatGPTResponse chat(ChatGPTRequest request) {
        String apiURL = environment.getProperty("openai.api.url");
        log.debug("Sending request to ChatGPT API: {}", request);
        ChatGPTResponse response = restTemplate.postForObject(apiURL, request, ChatGPTResponse.class);
        log.debug("Received response from ChatGPT API: {}", response);
        return response;
    }
}