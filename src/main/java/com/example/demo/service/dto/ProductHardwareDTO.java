package com.example.demo.service.dto;

public class ProductHardwareDTO {
    private String audioJack;
    private String gps;
    private String battery;

    public String getAudioJack() {
        return audioJack;
    }

    public void setAudioJack(String audioJack) {
        this.audioJack = audioJack;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductHardware{");
        sb.append("audioJack='").append(audioJack).append('\'');
        sb.append(", gps='").append(gps).append('\'');
        sb.append(", battery='").append(battery).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
