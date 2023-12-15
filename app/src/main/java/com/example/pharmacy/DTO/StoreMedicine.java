package com.example.pharmacy.DTO;

import java.sql.Date;

public class StoreMedicine {
    private Integer vendorCode;
    private Integer storeId;
    private Integer medicine_count;
    private Float unitPrice;
    private Date dateOfManufacture;
    private String deliveryStatusCol;

    public Integer getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Integer vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getMedicine_count() {
        return medicine_count;
    }

    public void setMedicine_count(Integer medicine_count) {
        this.medicine_count = medicine_count;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getDeliveryStatusCol() {
        return deliveryStatusCol;
    }

    public void setDeliveryStatusCol(String deliveryStatusCol) {
        this.deliveryStatusCol = deliveryStatusCol;
    }
}
