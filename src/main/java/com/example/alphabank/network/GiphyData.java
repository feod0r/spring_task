package com.example.alphabank.network;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class GiphyData {
    private Map[] data;
    private Map pagination;
    private Map meta;

    public GiphyData(Map[] data, Map pagination, Map meta) {
        this.data = data;
        this.pagination = pagination;
        this.meta = meta;
    }

    public Map getData() {
        return data[0];
    }

    public Map getPagination() {
        return pagination;
    }

    public Map getMeta() {
        return meta;
    }
}
