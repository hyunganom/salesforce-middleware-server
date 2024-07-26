package com.hyunganom.salesforcemiddleware.core.domain.salesforce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Salesforce 로그인 결과를 나타내는 클래스.
 * 액세스 토큰과 인스턴스 URL을 포함.
 */
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SalesforceLoginResult {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "instance_url")
    private String instanceUrl;
}