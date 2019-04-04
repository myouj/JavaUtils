//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ma.utils;

import java.math.BigDecimal;

public class PositionUtil {
    public static final String BAIDU_LBS_TYPE = "bd09ll";
    public static double pi = 3.141592653589793D;
    public static double a = 6378245.0D;
    public static double ee = 0.006693421622965943D;
    private static final double EARTH_RADIUS = 6370996.81D;
    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    public PositionUtil() {
    }

    //高德地图坐标转百度地图坐标
    public static Double[] toBdMap(Double x, Double y){
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;

        return new Double[]{bd_lon, bd_lat};
    }

    //高德地图坐标转百度地图坐标
    public static Double[] toBdMap(String x, String y){
        Double dx = Double.valueOf(x);
        Double dy = Double.valueOf(y);

        return toBdMap(dx, dy);
    }

    /**
     * 对double类型数据保留小数点后多少位
     *  高德地图转码返回的就是 小数点后6位，为了统一封装一下
     * @param digit 位数
     * @param in 输入
     * @return 保留小数位后的数
     */
    static double dataDigit(int digit,double in){
        return new BigDecimal(in).setScale(6,   BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 将火星坐标转变成百度坐标
     * @param（高德、腾讯地图坐标等）
     * @return 百度坐标
     */

    public static Double[] bd_encrypt(Double x, Double y) {
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x *  x_pi);
        return new Double[]{dataDigit(6,z * Math.cos(theta) + 0.0065), dataDigit(6,z * Math.sin(theta) + 0.006)};
    }

    /**
     * 将百度坐标转变成火星坐标（高德）
     *
     * @param x
     * @param y
     * @return
     */
    public static Double[] bd_decrypt(Double x, Double y)
    {
        x = x - 0.0065;
        y = y - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        return new Double[]{dataDigit(6,z * Math.cos(theta)),dataDigit(6,z * Math.sin(theta))};

    }

    public static PositionUtil.Gps gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return null;
        } else {
            double dLat = transformLat(lon - 105.0D, lat - 35.0D);
            double dLon = transformLon(lon - 105.0D, lat - 35.0D);
            double radLat = lat / 180.0D * pi;
            double magic = Math.sin(radLat);
            magic = 1.0D - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = dLat * 180.0D / (a * (1.0D - ee) / (magic * sqrtMagic) * pi);
            dLon = dLon * 180.0D / (a / sqrtMagic * Math.cos(radLat) * pi);
            double mgLat = lat + dLat;
            double mgLon = lon + dLon;
            return new PositionUtil.Gps(mgLat, mgLon);
        }
    }

    public static PositionUtil.Gps gcj_To_Gps84(double lat, double lon) {
        PositionUtil.Gps gps = transform(lat, lon);
        double lontitude = lon * 2.0D - gps.getWgLon();
        double latitude = lat * 2.0D - gps.getWgLat();
        return new PositionUtil.Gps(latitude, lontitude);
    }

    public static PositionUtil.Gps gcj02_To_Bd09(double gg_lat, double gg_lon) {
        double z = Math.sqrt(gg_lon * gg_lon + gg_lat * gg_lat) + 2.0E-5D * Math.sin(gg_lat * pi);
        double theta = Math.atan2(gg_lat, gg_lon) + 3.0E-6D * Math.cos(gg_lon * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065D;
        double bd_lat = z * Math.sin(theta) + 0.006D;
        return new PositionUtil.Gps(bd_lat, bd_lon);
    }

    public static PositionUtil.Gps bd09_To_Gcj02(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065D;
        double y = bd_lat - 0.006D;
        double z = Math.sqrt(x * x + y * y) - 2.0E-5D * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 3.0E-6D * Math.cos(x * pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new PositionUtil.Gps(gg_lat, gg_lon);
    }

    public static PositionUtil.Gps bd09_To_Gps84(double bd_lat, double bd_lon) {
        PositionUtil.Gps gcj02 = bd09_To_Gcj02(bd_lat, bd_lon);
        PositionUtil.Gps map84 = gcj_To_Gps84(gcj02.getWgLat(), gcj02.getWgLon());
        return map84;
    }

    public static PositionUtil.Gps Gps84_To_bd09(double wgs84_lat, double wgs84_lon) {
        PositionUtil.Gps gcj02 = gps84_To_Gcj02(wgs84_lat, wgs84_lon);
        PositionUtil.Gps bd09 = gcj02_To_Bd09(gcj02.getWgLat(), gcj02.getWgLon());
        return bd09;
    }

    public static boolean outOfChina(double lat, double lon) {
        if (lon >= 72.004D && lon <= 137.8347D) {
            return lat < 0.8293D || lat > 55.8271D;
        } else {
            return true;
        }
    }

    public static PositionUtil.Gps transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new PositionUtil.Gps(lat, lon);
        } else {
            double dLat = transformLat(lon - 105.0D, lat - 35.0D);
            double dLon = transformLon(lon - 105.0D, lat - 35.0D);
            double radLat = lat / 180.0D * pi;
            double magic = Math.sin(radLat);
            magic = 1.0D - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = dLat * 180.0D / (a * (1.0D - ee) / (magic * sqrtMagic) * pi);
            dLon = dLon * 180.0D / (a / sqrtMagic * Math.cos(radLat) * pi);
            double mgLat = lat + dLat;
            double mgLon = lon + dLon;
            return new PositionUtil.Gps(mgLat, mgLon);
        }
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0D + 2.0D * x + 3.0D * y + 0.2D * y * y + 0.1D * x * y + 0.2D * Math.sqrt(Math.abs(x));
        ret += (20.0D * Math.sin(6.0D * x * pi) + 20.0D * Math.sin(2.0D * x * pi)) * 2.0D / 3.0D;
        ret += (20.0D * Math.sin(y * pi) + 40.0D * Math.sin(y / 3.0D * pi)) * 2.0D / 3.0D;
        ret += (160.0D * Math.sin(y / 12.0D * pi) + 320.0D * Math.sin(y * pi / 30.0D)) * 2.0D / 3.0D;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0D + x + 2.0D * y + 0.1D * x * x + 0.1D * x * y + 0.1D * Math.sqrt(Math.abs(x));
        ret += (20.0D * Math.sin(6.0D * x * pi) + 20.0D * Math.sin(2.0D * x * pi)) * 2.0D / 3.0D;
        ret += (20.0D * Math.sin(x * pi) + 40.0D * Math.sin(x / 3.0D * pi)) * 2.0D / 3.0D;
        ret += (150.0D * Math.sin(x / 12.0D * pi) + 300.0D * Math.sin(x / 30.0D * pi)) * 2.0D / 3.0D;
        return ret;
    }

    private static double rad(double d) {
        return d * 3.141592653589793D / 180.0D;
    }

    public static double lantitudeLongitudeDist(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double radLon1 = rad(lon1);
        double radLon2 = rad(lon2);
        if (radLat1 < 0.0D) {
            radLat1 = 1.5707963267948966D + Math.abs(radLat1);
        }

        if (radLat1 > 0.0D) {
            radLat1 = 1.5707963267948966D - Math.abs(radLat1);
        }

        if (radLon1 < 0.0D) {
            radLon1 = 6.283185307179586D - Math.abs(radLon1);
        }

        if (radLat2 < 0.0D) {
            radLat2 = 1.5707963267948966D + Math.abs(radLat2);
        }

        if (radLat2 > 0.0D) {
            radLat2 = 1.5707963267948966D - Math.abs(radLat2);
        }

        if (radLon2 < 0.0D) {
            radLon2 = 6.283185307179586D - Math.abs(radLon2);
        }

        double x1 = 6378137.0D * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = 6378137.0D * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = 6378137.0D * Math.cos(radLat1);
        double x2 = 6378137.0D * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = 6378137.0D * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = 6378137.0D * Math.cos(radLat2);
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        double theta = Math.acos((8.1361263181538E13D - d * d) / 8.1361263181538E13D);
        double dist = theta * 6378137.0D;
        return dist;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
        s *= EARTH_RADIUS;
        s = (double)Math.round(s * 1000.0D);
        return s;
    }

    /**
     * 地图经纬度与实际距离（单位：米）换算公式
     * 有些许误差
     *
     * @param range 米
     * @return double[2]
     * [0]：经度 longitude
     * [1]：纬度 latitude
     */
    public static double[] distanceToLngLat(double range){
        double[] values = new double[2];
        values[0] = range*0.00001141;
        values[1] = range*0.00000899;

        return values;
    }

    public static class Gps {
        private double wgLat;
        private double wgLon;

        public Gps(double wgLat, double wgLon) {
            this.setWgLat(wgLat);
            this.setWgLon(wgLon);
        }

        public double getWgLat() {
            return this.wgLat;
        }

        public void setWgLat(double wgLat) {
            this.wgLat = wgLat;
        }

        public double getWgLon() {
            return this.wgLon;
        }

        public void setWgLon(double wgLon) {
            this.wgLon = wgLon;
        }

        public String toString() {
            return this.wgLat + "," + this.wgLon;
        }
    }
    
    public static void main(String[] args){
        double lng = 110.332128;
        double lat = 27.328371;
        Double[] test1 = PositionUtil.bd_encrypt(lng, lat);
        System.out.println(test1[0]+" "+test1[1]);
        Double[] test2 = PositionUtil.toBdMap(lng, lat);
        System.out.println(test2[0]+" "+test2[1]);
    }
    
}
