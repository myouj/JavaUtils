package com.ma.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;

public class GD2BD {

    static void readAndWrite2Location(String readFile, String writeFile) throws FileNotFoundException {
        int i = 0;
        //读csv
        CsvReader reader = new CsvReader(readFile, ',', Charset.forName("UTF-8"));
        //写csv
        CsvWriter writer = new CsvWriter(writeFile, ',', Charset.forName("UTF-8"));
        try {
            //跳过文件头
            reader.readHeaders();
            String[] heads = {"id", "address", "longitude", "latitude"};
            //写入文件头
            writer.writeRecord(heads);

            //判断一行是否有数据
            while (reader.readRecord()) {
                //string数组形式获取一行的数据
                String[] value = reader.getValues();
                String id = value[0];
                String address = value[1];
                String longitude = value[2];
                String latitude = value[3];

                System.out.println(address);

                Double[] toBdMap = PositionUtil.toBdMap(longitude, latitude);

                System.out.println(toBdMap.length);


                String[] writeLine = {id, address, toBdMap[0].toString(), toBdMap[1].toString()};

                //写入一行的数据
                writer.writeRecord(writeLine);


            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(i);
        }


        reader.close();
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        readAndWrite2Location("D:\\workspace\\utils\\spider\\src\\main\\resources\\zhuzhou_location.csv",
                "D:\\workspace\\utils\\spider\\src\\main\\resources\\zhuzhou_location-tobaidu.csv");
    }
}
