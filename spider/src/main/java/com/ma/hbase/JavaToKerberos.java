package com.ma.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * Java连接kerberos认证的HBase
 */
public class JavaToKerberos {

    private static Configuration conf = null;

    static {

        // 这个配置文件主要是记录 kerberos的相关配置信息，例如KDC是哪个IP？默认的realm是哪个？
        // 如果没有这个配置文件这边认证的时候肯定不知道KDC的路径喽
        // 这个文件也是从远程服务器上copy下来的
        System.setProperty("java.security.krb5.conf", "C:\\krb5.conf");
        System.setProperty("hadoop.home.dir", "E:\\winutils\\hadoop-common-2.6.0-bin");
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

    public static void scanSpan(final String tableName) throws Exception {
        HTable table = new HTable(conf, tableName);
        System.out.println("tablename:" + new String(table.getTableName()));
        Scan s = new Scan();
        ResultScanner rs = table.getScanner(s);

        for (Result r : rs) {
            System.out.println(r.toString());
            KeyValue[] kv = r.raw();
            for (int i = 0; i < kv.length; i++) {
                System.out.print(new String(kv[i].getRow()) + "");
                System.out.print(new String(kv[i].getFamily()) + ":");
                System.out.print(new String(kv[i].getQualifier()) + "");
                System.out.print(kv[i].getTimestamp() + "");
                System.out.println(new String(kv[i].getValue()));
            }
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
//        // TODO Auto-generated method stub
        try {
            scanSpan("hmx");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
