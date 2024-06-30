package com.hyunganom.salesforcemiddleware.core.ports.out;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SalesforceLoginResult;

public interface SalesforceAuthenticationPort {
    SalesforceLoginResult loginToSalesforce() throws Exception;
}