package com.example.task3_delta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Height {
    @SerializedName("imperial")
    @Expose
    private String imperial;
    @SerializedName("metric")
    @Expose
    private String metric;

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

}
