// utils/HttpUtils.java
package com.hyunganom.salesforcemiddleware.utils;

import org.apache.http.HttpResponse;

public final class HttpUtils {
    private HttpUtils() { }

    public static void checkResponse(HttpResponse httpResponse) throws Exception {
        // Simple validation to make sure the response is 2xx.
        if (httpResponse.getStatusLine().getStatusCode() < 200
                || httpResponse.getStatusLine().getStatusCode() > 299) {
            throw new Exception(httpResponse.getStatusLine().getReasonPhrase());
        }
    }
}