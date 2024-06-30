package com.hyunganom.salesforcemiddleware.core.ports.in;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;

public interface SalesforceUseCase {
    void sendToSalesforce(SendToSalesforce newChat);
    String handleSalesforceWebhook(String receiveChat);
}
