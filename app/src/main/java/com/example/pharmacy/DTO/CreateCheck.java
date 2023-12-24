package com.example.pharmacy.DTO;

public class CreateCheck {
    private Integer vendorCode;
    private String name;
    private Integer countMedicine;

    public Integer getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Integer vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountMedicine() {
        return countMedicine;
    }

    public void setCountMedicine(Integer countMedicine) {
        this.countMedicine = countMedicine;
    }

    public CreateCheck(Integer vendorCode, String name, Integer countMedicine) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.countMedicine = countMedicine;
    }
}
