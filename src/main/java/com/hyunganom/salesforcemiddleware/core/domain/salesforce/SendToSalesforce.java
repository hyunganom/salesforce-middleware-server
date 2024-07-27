package com.hyunganom.salesforcemiddleware.core.domain.salesforce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Salesforce로 전송할 데이터를 나타내는 클래스.
 * receive 필드를 포함.
 */
@Data
public class SendToSalesforce {

    @JsonProperty("Receive__c")
    private String receive;
}