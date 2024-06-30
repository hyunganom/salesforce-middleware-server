// core/domain/ReceiveFromSalesforce.java
package com.hyunganom.salesforcemiddleware.core.domain.salesforce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ReceiveFromSalesforce {

    @JsonProperty("send__c")
    String send__c;

}