package com.ma.csv;

import com.csvreader.CsvReader;

import java.nio.charset.Charset;

public class read {

    static void readCsv(String filePath) throws Exception {
        CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));

        reader.readHeaders();

        int i = 0;

        while (reader.readRecord()){
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
        readCsv("D:\\workspace\\utils\\spider\\src\\main\\resources\\shaoyang_address.csv");
    }

}
