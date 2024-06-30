// ports/OutboundPort.java
package com.hyunganom.salesforcemiddleware.core.ports.out;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;

public interface SalesforceOutboundPort {
    void sendChat(SendToSalesforce sendChat) throws Exception;
}