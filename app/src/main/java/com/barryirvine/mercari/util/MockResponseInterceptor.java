package com.barryirvine.mercari.util;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * This class allows me to implement the Items calls
 * as if they were pointing to a real API service and return the results of the appropriate JSON file
 */
public class MockResponseInterceptor implements Interceptor {

    private static final int BUFFER_SIZE = 1024 * 4;

    private Context context;

    public MockResponseInterceptor(final Context context) {
        this.context = context.getApplicationContext();
    }

    private static byte[] toByteArray(final InputStream is) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            byte[] b = new byte[BUFFER_SIZE];
            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            return output.toByteArray();
        } finally {
            output.close();
        }
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final String fileName = chain.request().url().uri().getPath().replace("/", "");
        final int resourceId = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());

        // Get input stream and mime type for mock response file.
        final InputStream inputStream = context.getResources().openRawResource(resourceId);
        // Build and return mock response.
        return new Response.Builder()
                .addHeader("content-type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), toByteArray(inputStream)))
                .code(200)
                .message("Mock response from res/raw/" + fileName)
                .protocol(Protocol.HTTP_1_0)
                .request(chain.request())
                .build();
    }
}
