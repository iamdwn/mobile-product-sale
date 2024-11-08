package com.mobile.productsale.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class RetryInterceptor implements Interceptor {
    private int maxRetries;
    private int retryCount = 0;

    public RetryInterceptor(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        boolean successful = false;

        while (!successful && retryCount < maxRetries) {
            try {
                response = chain.proceed(request);
                successful = response.isSuccessful();
            } catch (IOException e) {
                if (retryCount >= maxRetries) {
                    throw e;
                }
            }
            retryCount++;
        }

        if (response == null) {
            throw new IOException("Failed to execute request after " + maxRetries + " retries");
        }

        return response;
    }
}

