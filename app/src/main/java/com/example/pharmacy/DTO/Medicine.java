package com.example.pharmacy.DTO;

public class Medicine {
    private String name;
    private Integer categoryId;
    private String ManufactureCountry;
    private String ManufactureCompany;
    private Integer countInPackage;
    private Boolean prescription;
    private String description;
    private Integer expirationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getManufactureCountry() {
        return ManufactureCountry;
    }

    public void setManufactureCountry(String manufactureCountry) {
        ManufactureCountry = manufactureCountry;
    }

    public String getManufactureCompany() {
        return ManufactureCompany;
    }

    public void setManufactureCompany(String manufactureCompany) {
        ManufactureCompany = manufactureCompany;
    }

    public Integer getCountInPackage() {
        return countInPackage;
    }

    public void setCountInPackage(Integer countInPackage) {
        this.countInPackage = countInPackage;
    }

    public Boolean getPrescription() {
        return prescription;
    }

    public void setPrescription(Boolean prescription) {
        this.prescription = prescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }
}
