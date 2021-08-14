package com.example.task3_delta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainInterface {

    @Headers("x-api-key: e1a18a12-22fa-436c-a38c-e536a841c7b4")
    @GET("breeds")
    Call<ArrayList<MainItem>> getMainItem(
            @Query("limit")Integer limit,
            @Query("page")Integer page
    );

    @Multipart
    @Headers("x-api-key: e1a18a12-22fa-436c-a38c-e536a841c7b4")
    @POST("images/upload")
    Call<PhotoUploadModel> uploadImage(@Part MultipartBody.Part file);

    @Headers("x-api-key: e1a18a12-22fa-436c-a38c-e536a841c7b4")
    @GET("images/{image_id}/analysis")
    Call<ArrayList<ImageAnalysisModel>> analyseImage(@Path("image_id") String breedId);

}
