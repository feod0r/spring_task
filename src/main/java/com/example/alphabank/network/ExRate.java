package com.example.alphabank.network;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExRate {
    private String disclaimer;
    private String license;
    private int timestamp;
    private String base;
    private Map<String, Float> rates;

    public ExRate(String disclaimer, String license, int timestamp, String base, Map<String, Float> rates) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public Map<String, Float> getRates() {
        return rates;
    }
}
