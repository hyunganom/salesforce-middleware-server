package com.hyunganom.salesforcemiddleware.core.ports.out;

import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTRequest;
import com.hyunganom.salesforcemiddleware.core.domain.chatgpt.ChatGPTResponse;

public interface ChatGPTAPIClient {
    ChatGPTResponse chat(ChatGPTRequest request);
}
