// core/domain/SalesforceAttributes.java
package com.hyunganom.salesforcemiddleware.core.domain.salesforce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SalesforceAttributes {
    private String type;
    private String url;
}