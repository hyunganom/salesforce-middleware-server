// controllers/ChatGPTController.java
package com.hyunganom.salesforcemiddleware.adapters.in.web;

import com.hyunganom.salesforcemiddleware.core.ports.in.ChatGPTUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gpt")
public class ChatGPTController {

    private final ChatGPTUseCase chatGPTUseCase;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt") String prompt) {
        String response = chatGPTUseCase.chat(prompt);
        log.debug("ChatGPTResponse: {}", response);
        return response;
    }
}