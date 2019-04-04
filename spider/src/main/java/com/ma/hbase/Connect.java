package com.ma.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * java 连接HBase
 */
public class Connect {

    private static Configuration config;
    private static Admin admin;
    private static Connection connection;

    static {
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "10.18.0.12");
        config.set("hbase.zookeeper.property.clientPort", "2181");

        System.setProperty("hadoop.home.dir", "E:\\winutils\\hadoop-common-2.6.0-bin");
        try {
            connection = ConnectionFactory.createConnection(config);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static Connection getConnection() throws IOException {
        if (connection != null) {
            connection = ConnectionFactory.createConnection(config);
        }
        return connection;
    }

    static void createTable() throws Exception {
        // 创建表管理类
        HBaseAdmin admin = new HBaseAdmin(config); // hbase表管理
        // 创建表描述类
        TableName tableName = TableName.valueOf("dept"); // 表名称
        HTableDescriptor desc = new HTableDescriptor(tableName);
        // 创建列族的描述类
        HColumnDescriptor family = new HColumnDescriptor("info"); // 列族
        // 将列族添加到表中
        desc.addFamily(family);
        HColumnDescriptor family2 = new HColumnDescriptor("subdept"); // 列族
        // 将列族添加到表中
        desc.addFamily(family2);
        // 创建表
        admin.createTable(desc); // 创建表
        System.out.println("创建表成功！");
    }

    public static void main(String[] args) throws Exception {


        createTable();

        Connection connection = getConnection();
        System.out.println(connection);
        Admin admin = connection.getAdmin();
        if (admin != null) {
            HTableDescriptor[] allTable = admin.listTables();
            for (HTableDescriptor table :
                    allTable) {
                System.out.println(table.getNameAsString());
            }
        }

        connection.close();

    }

}
