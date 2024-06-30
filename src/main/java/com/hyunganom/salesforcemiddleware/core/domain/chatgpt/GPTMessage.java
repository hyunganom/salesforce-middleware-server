// core/domain/GPTMessage.java
package com.hyunganom.salesforcemiddleware.core.domain.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTMessage {
    private String role;
    private String content;
}