// core/domain/SendToSalesforce.java
package com.hyunganom.salesforcemiddleware.core.domain.salesforce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendToSalesforce {
    @JsonProperty("Receive__c")
    private String receive;
}