package com.hyunganom.salesforcemiddleware.core.ports.out;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SalesforceLoginResult;

/**
 * Salesforce 인증을 위한 포트 인터페이스.
 * Salesforce에 로그인 처리.
 */
public interface SalesforceAuthenticationPort {
    /**
     * Salesforce에 로그인하고 로그인 결과를 반환.
     *
     * @return Salesforce 로그인 결과 객체
     * @throws Exception 로그인 중 발생할 수 있는 예외
     */
    SalesforceLoginResult loginToSalesforce() throws Exception;
}