package com.example.dockerhealth.dto;

public class HealthDto {

    private String status;

    public HealthDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
