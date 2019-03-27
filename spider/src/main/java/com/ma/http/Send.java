package com.ma.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Send {

    public static void main(String[] args) throws IOException {

        String url = "https://restapi.amap.com/v3/geocode/geo?";
        StringBuffer sb = new StringBuffer(url);

        sb.append("key=e014a9822a6b64fcfe2b46af183ae62c");
        sb.append("&address=信息枢纽楼");
        sb.append("&city=广州");

        String result = Jsoup.connect(sb.toString()).timeout(10000).ignoreContentType(true).execute().body();
        System.out.println(result);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PointInfo poi = objectMapper.readValue(result, PointInfo.class);

        System.out.println(poi.toString());



    }


}
