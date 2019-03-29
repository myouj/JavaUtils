package com.ma.csv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ma.http.Geocode;
import com.ma.http.PointInfo;
import org.jsoup.Jsoup;

import java.nio.charset.Charset;

public class read {

    /**
     * 读写csv文件
     * @param readFile
     * @param writeFile
     * @throws Exception
     */
    static void readAndWriteCsv(String readFile, String writeFile) throws Exception {
        int i = 0;
        //读csv
        CsvReader reader = new CsvReader(readFile, ',', Charset.forName("UTF-8"));
        //写csv
        CsvWriter writer = new CsvWriter(writeFile, ',', Charset.forName("UTF-8"));
        try {
            //跳过文件头
            reader.readHeaders();
            String[] heads = {"id", "address", "location"};
            //写入文件头
            writer.writeRecord(heads);
            PointInfo poi;
            Geocode geocode;
            //判断一行是否有数据
            while (reader.readRecord()) {
                //string数组形式获取一行的数据
                String[] value = reader.getValues();
                String id = value[0];
                String address = value[5];


                String url = "https://restapi.amap.com/v3/geocode/geo?";
                StringBuffer sb = new StringBuffer(url);

                sb.append("key=e014a9822a6b64fcfe2b46af183ae62c");
                sb.append("&address=" + address);
                sb.append("&city=邵阳市");


                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                poi = objectMapper.readValue(Jsoup.connect(sb.toString()).timeout(10000).ignoreContentType(true).execute().body()
                        .replace("\"address\":[]", "\"address\": \"\"")
                        .replace("\"address\":{}", "\"address\": \"\"")
                        .replace("\"pois\":{}", "\"pois\":[]").replace("\"district\":[]"
                                , "\"district\":\"\""), PointInfo.class);
                try {
                    geocode = poi.getGeocodes().get(0);
                    String[] writeLine = {id, address, ""};
                    if (geocode != null) {
                        writeLine[2] = geocode.getLocation();
                    }
                    //写入一行的数据
                    writer.writeRecord(writeLine);

                } catch (IndexOutOfBoundsException e) {
                    i++;
                    System.out.println("行数：" + id);
                } catch (NullPointerException e) {
                    i++;
                    e.printStackTrace();
                    System.out.println("行数：" + id);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(i);
        }


        System.out.println("信息查询无结果数量：" + i);

        reader.close();
        writer.close();
    }

    /**
     * 读csv文件
     * @param filePath
     * @throws Exception
     */
    static void readCsv(String filePath) throws Exception {
        //创建读对象
        CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
        //跳过表头信息
        reader.readHeaders();

        int i = 0;

        //读下一行信息，有为true
        while (reader.readRecord()) {
            String[] value = reader.getValues();
            System.out.println("id:" + value[0] + "\t");
            System.out.println("sale_center_id:" + value[1] + "\t");
            System.out.println("country:" + value[2] + "\t");
            System.out.println("cust_id:" + value[3] + "\t");
            System.out.println("busi_addr:" + value[4] + "\t");
            System.out.println("address:" + value[5] + "\n");
            i++;
        }

        System.out.println("行数：" + i);


        reader.close();
    }

    public static void main(String[] args) throws Exception {
        readAndWriteCsv("D:\\workspace\\utils\\spider\\src\\main\\resources\\shaoyang_address.csv",
                "D:\\workspace\\utils\\spider\\src\\main\\resources\\out.csv");
    }

}
