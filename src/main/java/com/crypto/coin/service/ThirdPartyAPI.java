package com.crypto.coin.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class ThirdPartyAPI {
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION = "Authorization";

    private static final String SYS_HTTP_PROXY_HOST = "http.proxyHost";
    private static final String SYS_HTTP_PROXY_PORT = "http.proxyPort";
    private static final String SYS_HTTPS_PROXY_HOST = "https.proxyHost";
    private static final String SYS_HTTPS_PROXY_PORT = "https.proxyPort";

    public ThirdPartyAPI() {
    }

    public void setUpProxy(String proxyHost, String proxyPort) {
        if (null != proxyHost && null != proxyPort) {
            System.setProperty(SYS_HTTP_PROXY_HOST, proxyHost);
            System.setProperty(SYS_HTTP_PROXY_PORT, proxyPort);
            System.setProperty(SYS_HTTPS_PROXY_HOST, proxyHost);
            System.setProperty(SYS_HTTPS_PROXY_PORT, proxyPort);
        }
    }

    public Response get(String url, String token, String proxyHost, String proxyPort) throws IOException {
        setUpProxy(proxyHost, proxyPort);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        String reqBody = "";
        Request requestHttp = new Request.Builder()
                .url(url)
                .method(GET, null)
                .addHeader(AUTHORIZATION, token)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();
        Response response = client.newCall(requestHttp).execute();
        return response;
    }

    public Response post(String url, String requestBody, String token, String proxyHost, String proxyPort) throws IOException {
        setUpProxy(proxyHost, proxyPort);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request requestHttp = new Request.Builder()
                .url(url)
                .method(POST, body)
                .addHeader(AUTHORIZATION, token)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();
        Response response = client.newCall(requestHttp).execute();
        return response;
    }

    public Response getWithoutToken(String url, String proxyHost, String proxyPort) throws IOException {
        setUpProxy(proxyHost, proxyPort);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request requestHttp = new Request.Builder()
                .url(url)
                .method(GET, null)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();
        Response response = client.newCall(requestHttp).execute();
        return response;
    }

    public Response postWithoutToken(String url, String requestBody, String proxyHost, String proxyPort) throws IOException {
        setUpProxy(proxyHost, proxyPort);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request requestHttp = new Request.Builder()
                .url(url)
                .method(POST, body)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();
        Response response = client.newCall(requestHttp).execute();
        return response;
    }

    public Response put(String url, String requestBody, String token, String proxyHost, String proxyPort) throws IOException {
        setUpProxy(proxyHost, proxyPort);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request requestHttp = new Request.Builder()
                .url(url)
                .method(PUT, body)
                .addHeader(AUTHORIZATION, token)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();
        Response response = client.newCall(requestHttp).execute();
        return response;
    }

    public Response delete(String url, String token, String proxyHost, String proxyPort) throws IOException {
        setUpProxy(proxyHost, proxyPort);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        Request requestHttp = new Request.Builder()
                .url(url)
                .method(DELETE, null)
                .addHeader(AUTHORIZATION, token)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();
        Response response = client.newCall(requestHttp).execute();
        return response;
    }

    public Response postMultipartFile(String url, MultipartFile file, String token, String proxyHost,
                                      String proxyPort) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+ file.getOriginalFilename());
        file.transferTo(convFile);
        setUpProxy(proxyHost, proxyPort);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);
//        byte[] bytes = file.getBytes();
        MultipartBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("file", file.getOriginalFilename(), RequestBody.create(mediaType, convFile))
                .setType(MultipartBody.FORM)
                .build();
        Request requestHttp = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader(AUTHORIZATION, token)
                .build();
        Response response = client.newCall(requestHttp).execute();
        return response;
    }
}


