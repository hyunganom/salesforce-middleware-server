// core/domain/GPTMessage.java
package com.hyunganom.salesforcemiddleware.core.domain.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChatGPT API에 전송할 메시지를 나타내는 클래스.
 * 역할과 내용을 포함.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTMessage {
    private String role;
    private String content;
}