package com.example.pharmacy.DTO;

import java.sql.Date;

public class EmployeeStore {
    private Float salary;
    private Date hireDate;
    private Integer positionId;
    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
