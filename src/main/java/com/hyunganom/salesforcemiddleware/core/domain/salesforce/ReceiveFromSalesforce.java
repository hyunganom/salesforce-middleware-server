// core/domain/ReceiveFromSalesforce.java
package com.hyunganom.salesforcemiddleware.core.domain.salesforce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Salesforce로부터 수신된 데이터를 나타내는 클래스.
 * send__c 필드를 포함.
 */
@Data
public class ReceiveFromSalesforce {

    @JsonProperty("send__c")
    private String send__c;
}