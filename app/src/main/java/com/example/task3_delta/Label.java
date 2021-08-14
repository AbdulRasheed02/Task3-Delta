package com.example.task3_delta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Label {
    @SerializedName("Confidence")
    @Expose
    private Double confidence;
    @SerializedName("Instances")
    @Expose
    private List<Instance> instances = null;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Parents")
    @Expose
    private List<Parent> parents = null;

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}
