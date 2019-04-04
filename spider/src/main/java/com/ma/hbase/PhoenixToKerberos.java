package com.ma.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * phoenix连接Kerberos认证的HBase
 */
public class PhoenixToKerberos {

    private static Configuration conf = null;

    static {

        // 这个配置文件主要是记录 kerberos的相关配置信息，例如KDC是哪个IP？默认的realm是哪个？
        // 如果没有这个配置文件这边认证的时候肯定不知道KDC的路径喽
        // 这个文件也是从远程服务器上copy下来的
        System.setProperty("java.security.krb5.conf", "C:\\krb5.conf");

        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "10.18.0.12");// zookeeper地址
        conf.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
        conf.set("hadoop.security.authentication", "Kerberos");
        conf.set("hbase.security.authentication", "Kerberos");
        conf.set("zookeeper.znode.parent", "/hbase");
        conf.set("hbase.regionserver.kerberos.principal", "hbase/_HOST@HADOOP.COM");
        conf.set("hbase.master.kerberos.principal", "hbase/entrobus12@HADOOP.COM");

        System.out.println(conf);

        UserGroupInformation.setConfiguration(conf);
        try {
            UserGroupInformation ugi = UserGroupInformation.loginUserFromKeytabAndReturnUGI("bgy@HADOOP.COM", "C:/bgy.keytab");
            UserGroupInformation.setLoginUser(ugi);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 使用phoenix提供的api操作hbase读取数据
     */
    public static void main(String[] args) throws Throwable {

        try {

            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 这里配置zookeeper的地址，可单个，也可多个。可以是域名或者ip
        String url = "jdbc:phoenix:10.18.0.12";

        Properties properties = new Properties();

        properties.put("hbase.zookeeper.quorum", "10.18.0.12");// zookeeper地址
        properties.put("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
        properties.put("hadoop.security.authentication", "kerberos");
        properties.put("hbase.security.authentication", "kerberos");
        properties.put("zookeeper.znode.parent", "/hbase");
        properties.put("hbase.regionserver.kerberos.principal", "hbase/_HOST@HADOOP.COM");
        properties.put("hbase.master.kerberos.principal", "hbase/entrobus12@HADOOP.COM");
        properties.setProperty("hbase.myclient.keytab", "C:/bgy.keytab");

        Connection conn = DriverManager.getConnection(url, properties);
        Statement statement = conn.createStatement();
        String sql = "select count(1) as num from SYSTEM.CATALOG";

        long time = System.currentTimeMillis();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int count = rs.getInt("num");
            System.out.println("row count is " + count);
        }
        long timeUsed = System.currentTimeMillis() - time;
        System.out.println("time " + timeUsed + "mm");
        // 关闭连接
        rs.close();
        statement.close();
        conn.close();
    }

}
