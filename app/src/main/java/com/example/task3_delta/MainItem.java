package com.example.task3_delta;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainItem {
    @SerializedName("weight")
    @Expose
    private Weight weight;
    @SerializedName("height")
    @Expose
    private Height height;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bred_for")
    @Expose
    private String bredFor;
    @SerializedName("breed_group")
    @Expose
    private String breedGroup;
    @SerializedName("life_span")
    @Expose
    private String lifeSpan;
    @SerializedName("temperament")
    @Expose
    private String temperament;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("reference_image_id")
    @Expose
    private String referenceImageId;
    @SerializedName("image")
    @Expose
    private ImageDog imageDog;
    @SerializedName("country_code")
    @Expose
    private String countryCode;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getReferenceImageId() {
        return referenceImageId;
    }

    public void setReferenceImageId(String referenceImageId) {
        this.referenceImageId = referenceImageId;
    }

    public ImageDog getImageDog() {
        return imageDog;
    }

    public void setImageDog(ImageDog imageDog) {
        this.imageDog = imageDog;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
