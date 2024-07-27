package com.hyunganom.salesforcemiddleware.core.ports.in;

/**
 * ChatGPT와 상호 작용하는 Use Case 인터페이스.
 * 주어진 프롬프트를 기반으로 채팅 응답을 생성.
 */
public interface ChatGPTUseCase {
    /**
     * 주어진 프롬프트를 사용하여 ChatGPT와 대화하고 응답을 반환.
     *
     * @param prompt 사용자 입력 프롬프트
     * @return ChatGPT의 응답
     */
    String chat(String prompt);
}