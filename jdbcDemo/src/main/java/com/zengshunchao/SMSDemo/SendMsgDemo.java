package com.zengshunchao.SMSDemo;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 获取短信验证码demo
 */
public class SendMsgDemo {

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
        NameValuePair data[] = {new NameValuePair("Uid", "Mr.zeng"), new NameValuePair("Key", "d41d8cd98f00b204e980"), new NameValuePair("smsMob", "18227593852"), new NameValuePair("smsText", "验证码：8888")};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header headers[] = post.getResponseHeaders();
        int stateCode = post.getStatusCode();

        System.out.println("状态码:" + stateCode);

        for (Header header : headers) {

            System.out.println(header.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));

        System.out.println(result);

        post.releaseConnection();
    }
}
