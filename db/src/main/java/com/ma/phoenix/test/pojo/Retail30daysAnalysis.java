package com.ma.phoenix.test.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author autoGenerate
 * @since 2019-04-03
 */
@TableName("retail_30days_analysis")
public class Retail30daysAnalysis extends Model<Retail30daysAnalysis> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String retailId;

    private Integer sum;

    private Double amount;

    private Double price;

    private Integer orders;

    private String cigaCat;

    private Double outProvCigaRatio;

    private Double highRatio;

    private Double ordersStreamRatio;

    private Double sumChangeLyear;

    private Double amountChangeLyear;

    private Double priceChangeLyear;

    private Double outProvCigaRatioChangeLyear;

    private Double highRatioChangeLyear;

    private Double sumChangeLmonth;

    private Double amountChangeLmonth;

    private Double priceChangeLmonth;

    private Double outProvCigaRatioChangeLmonth;

    private Double highRatioChangeLmonth;

    private Double orderSumWithKm;

    private Double orderAmountWithKm;

    private Double highWithKm;

    private Double outProvWithKm;

    private Double ordersStreamWithCity;

    private Double priceConsWithCity;

    private Double gradeStreamWithCity;

    private Double priceWithKmHpriceWithCity;

    private Double outProvOrderWithKm;

    private Double outProvCigaStreamWithCity;

    private Double posSumOrdersWithCity;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailId() {
        return retailId;
    }

    public void setRetailId(String retailId) {
        this.retailId = retailId;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getCigaCat() {
        return cigaCat;
    }

    public void setCigaCat(String cigaCat) {
        this.cigaCat = cigaCat;
    }

    public Double getOutProvCigaRatio() {
        return outProvCigaRatio;
    }

    public void setOutProvCigaRatio(Double outProvCigaRatio) {
        this.outProvCigaRatio = outProvCigaRatio;
    }

    public Double getHighRatio() {
        return highRatio;
    }

    public void setHighRatio(Double highRatio) {
        this.highRatio = highRatio;
    }

    public Double getOrdersStreamRatio() {
        return ordersStreamRatio;
    }

    public void setOrdersStreamRatio(Double ordersStreamRatio) {
        this.ordersStreamRatio = ordersStreamRatio;
    }

    public Double getSumChangeLyear() {
        return sumChangeLyear;
    }

    public void setSumChangeLyear(Double sumChangeLyear) {
        this.sumChangeLyear = sumChangeLyear;
    }

    public Double getAmountChangeLyear() {
        return amountChangeLyear;
    }

    public void setAmountChangeLyear(Double amountChangeLyear) {
        this.amountChangeLyear = amountChangeLyear;
    }

    public Double getPriceChangeLyear() {
        return priceChangeLyear;
    }

    public void setPriceChangeLyear(Double priceChangeLyear) {
        this.priceChangeLyear = priceChangeLyear;
    }

    public Double getOutProvCigaRatioChangeLyear() {
        return outProvCigaRatioChangeLyear;
    }

    public void setOutProvCigaRatioChangeLyear(Double outProvCigaRatioChangeLyear) {
        this.outProvCigaRatioChangeLyear = outProvCigaRatioChangeLyear;
    }

    public Double getHighRatioChangeLyear() {
        return highRatioChangeLyear;
    }

    public void setHighRatioChangeLyear(Double highRatioChangeLyear) {
        this.highRatioChangeLyear = highRatioChangeLyear;
    }

    public Double getSumChangeLmonth() {
        return sumChangeLmonth;
    }

    public void setSumChangeLmonth(Double sumChangeLmonth) {
        this.sumChangeLmonth = sumChangeLmonth;
    }

    public Double getAmountChangeLmonth() {
        return amountChangeLmonth;
    }

    public void setAmountChangeLmonth(Double amountChangeLmonth) {
        this.amountChangeLmonth = amountChangeLmonth;
    }

    public Double getPriceChangeLmonth() {
        return priceChangeLmonth;
    }

    public void setPriceChangeLmonth(Double priceChangeLmonth) {
        this.priceChangeLmonth = priceChangeLmonth;
    }

    public Double getOutProvCigaRatioChangeLmonth() {
        return outProvCigaRatioChangeLmonth;
    }

    public void setOutProvCigaRatioChangeLmonth(Double outProvCigaRatioChangeLmonth) {
        this.outProvCigaRatioChangeLmonth = outProvCigaRatioChangeLmonth;
    }

    public Double getHighRatioChangeLmonth() {
        return highRatioChangeLmonth;
    }

    public void setHighRatioChangeLmonth(Double highRatioChangeLmonth) {
        this.highRatioChangeLmonth = highRatioChangeLmonth;
    }

    public Double getOrderSumWithKm() {
        return orderSumWithKm;
    }

    public void setOrderSumWithKm(Double orderSumWithKm) {
        this.orderSumWithKm = orderSumWithKm;
    }

    public Double getOrderAmountWithKm() {
        return orderAmountWithKm;
    }

    public void setOrderAmountWithKm(Double orderAmountWithKm) {
        this.orderAmountWithKm = orderAmountWithKm;
    }

    public Double getHighWithKm() {
        return highWithKm;
    }

    public void setHighWithKm(Double highWithKm) {
        this.highWithKm = highWithKm;
    }

    public Double getOutProvWithKm() {
        return outProvWithKm;
    }

    public void setOutProvWithKm(Double outProvWithKm) {
        this.outProvWithKm = outProvWithKm;
    }

    public Double getOrdersStreamWithCity() {
        return ordersStreamWithCity;
    }

    public void setOrdersStreamWithCity(Double ordersStreamWithCity) {
        this.ordersStreamWithCity = ordersStreamWithCity;
    }

    public Double getPriceConsWithCity() {
        return priceConsWithCity;
    }

    public void setPriceConsWithCity(Double priceConsWithCity) {
        this.priceConsWithCity = priceConsWithCity;
    }

    public Double getGradeStreamWithCity() {
        return gradeStreamWithCity;
    }

    public void setGradeStreamWithCity(Double gradeStreamWithCity) {
        this.gradeStreamWithCity = gradeStreamWithCity;
    }

    public Double getPriceWithKmHpriceWithCity() {
        return priceWithKmHpriceWithCity;
    }

    public void setPriceWithKmHpriceWithCity(Double priceWithKmHpriceWithCity) {
        this.priceWithKmHpriceWithCity = priceWithKmHpriceWithCity;
    }

    public Double getOutProvOrderWithKm() {
        return outProvOrderWithKm;
    }

    public void setOutProvOrderWithKm(Double outProvOrderWithKm) {
        this.outProvOrderWithKm = outProvOrderWithKm;
    }

    public Double getOutProvCigaStreamWithCity() {
        return outProvCigaStreamWithCity;
    }

    public void setOutProvCigaStreamWithCity(Double outProvCigaStreamWithCity) {
        this.outProvCigaStreamWithCity = outProvCigaStreamWithCity;
    }

    public Double getPosSumOrdersWithCity() {
        return posSumOrdersWithCity;
    }

    public void setPosSumOrdersWithCity(Double posSumOrdersWithCity) {
        this.posSumOrdersWithCity = posSumOrdersWithCity;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Retail30daysAnalysis{" +
        "id=" + id +
        ", retailId=" + retailId +
        ", sum=" + sum +
        ", amount=" + amount +
        ", price=" + price +
        ", orders=" + orders +
        ", cigaCat=" + cigaCat +
        ", outProvCigaRatio=" + outProvCigaRatio +
        ", highRatio=" + highRatio +
        ", ordersStreamRatio=" + ordersStreamRatio +
        ", sumChangeLyear=" + sumChangeLyear +
        ", amountChangeLyear=" + amountChangeLyear +
        ", priceChangeLyear=" + priceChangeLyear +
        ", outProvCigaRatioChangeLyear=" + outProvCigaRatioChangeLyear +
        ", highRatioChangeLyear=" + highRatioChangeLyear +
        ", sumChangeLmonth=" + sumChangeLmonth +
        ", amountChangeLmonth=" + amountChangeLmonth +
        ", priceChangeLmonth=" + priceChangeLmonth +
        ", outProvCigaRatioChangeLmonth=" + outProvCigaRatioChangeLmonth +
        ", highRatioChangeLmonth=" + highRatioChangeLmonth +
        ", orderSumWithKm=" + orderSumWithKm +
        ", orderAmountWithKm=" + orderAmountWithKm +
        ", highWithKm=" + highWithKm +
        ", outProvWithKm=" + outProvWithKm +
        ", ordersStreamWithCity=" + ordersStreamWithCity +
        ", priceConsWithCity=" + priceConsWithCity +
        ", gradeStreamWithCity=" + gradeStreamWithCity +
        ", priceWithKmHpriceWithCity=" + priceWithKmHpriceWithCity +
        ", outProvOrderWithKm=" + outProvOrderWithKm +
        ", outProvCigaStreamWithCity=" + outProvCigaStreamWithCity +
        ", posSumOrdersWithCity=" + posSumOrdersWithCity +
        "}";
    }
}
