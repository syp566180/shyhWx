//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.test;

import com.jsruiyin.wechat.utils.MyX509TrustManagerUtil;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;

public class TokenTest {
    public TokenTest() {
    }

    public static void main(String[] args) throws Exception {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx1a4e3c5b7c5c1619&secret=60529f9425364826964d4d346247fa37";
        URL url = new URL(tokenUrl);
        HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
        TrustManager[] tm = new TrustManager[]{new MyX509TrustManagerUtil()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init((KeyManager[])null, tm, new SecureRandom());
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        httpUrlConn.setSSLSocketFactory(ssf);
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);
        httpUrlConn.setRequestMethod("GET");
        InputStream inputStream = httpUrlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer buffer = new StringBuffer();
        String str = null;

        while((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        httpUrlConn.disconnect();
        System.out.println(buffer);
    }
}
