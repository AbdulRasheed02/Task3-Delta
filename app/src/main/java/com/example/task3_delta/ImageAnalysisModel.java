package com.example.task3_delta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageAnalysisModel {
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("image_id")
    @Expose
    private String imageId;
    @SerializedName("labels")
    @Expose
    private List<Label> labels = null;
    @SerializedName("moderation_labels")
    @Expose
    private List<Object> moderationLabels = null;
    @SerializedName("vendor")
    @Expose
    private String vendor;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Object> getModerationLabels() {
        return moderationLabels;
    }

    public void setModerationLabels(List<Object> moderationLabels) {
        this.moderationLabels = moderationLabels;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

}
