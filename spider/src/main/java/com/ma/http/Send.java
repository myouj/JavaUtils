package com.ma.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Send {

    /**
     * 请求 api 接口
     * @param url 接口
     * @param params 参数 list形式，多参数需要加 &
     * @param clazz 返回的封装对象
     * @return
     * @throws IOException
     */
    static Object getApi(String url, List<String> params, Class clazz) throws IOException {
        StringBuffer sb = new StringBuffer(url);
        for(int i = 0; i < params.size(); i++){
            sb.append(params.get(i));
        }
        //发起请求
        String result = Jsoup.connect(sb.toString()).timeout(10000).ignoreContentType(true).execute().body();
        //封装数据类型
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Object obj = objectMapper.readValue(result, clazz);

        return obj;
    }

    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<String>();
        list.add("key=e014a9822a6b64fcfe2b46af183ae62c");
        list.add("&address=信息枢纽楼");
        list.add("&city=广州");

        PointInfo poi = (PointInfo) getApi("https://restapi.amap.com/v3/geocode/geo?", list, PointInfo.class);

        System.out.println(poi.toString());

//        StringBuffer sb = new StringBuffer("https://restapi.amap.com/v3/geocode/geo?");
//        sb.append("key=e014a9822a6b64fcfe2b46af183ae62c");
//        sb.append("&address=信息枢纽楼");
//        sb.append("&city=广州");
//
//        String result = Jsoup.connect(sb.toString()).timeout(10000).ignoreContentType(true).execute().body();
//        System.out.println(result);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        PointInfo poi = objectMapper.readValue(result, PointInfo.class);
//
//        System.out.println(poi.toString());

    }


}
