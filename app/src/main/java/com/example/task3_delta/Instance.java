package com.example.task3_delta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instance {
    @SerializedName("BoundingBox")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("Confidence")
    @Expose
    private Double confidence;

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}
