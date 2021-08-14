package com.example.task3_delta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageAnalysisActivity extends AppCompatActivity {

    private static final String TAG = "Upload";
    private static final String TAG1 = "Analyse";
    ImageView uploadedImage;
    Button btn_Select, btn_Upload;
    TextView uploadedBreed;
    ProgressBar progressBar;

    Boolean canUpload;
    Boolean canAnalyse;
    Boolean newImageUploaded;

    MainInterface mainInterface;
    PhotoUploadModel photoUploadModel;
    private ArrayList<ImageAnalysisModel> mImageAnalysisModel;

    private int PICK_IMAGE_REQUEST = 1;
    Uri fileUri;
    InputStream is;

    String image_id;

    Retrofit retrofit2;
    Object object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_analysis);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("FIND DOG BREED");

        uploadedImage = findViewById(R.id.uploadedImage);
        btn_Select = findViewById(R.id.btn_uploadImage);
        btn_Upload = findViewById(R.id.btn_findBreed);
        uploadedBreed = findViewById(R.id.tv_uploadedbreed);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.INVISIBLE);

        uploadedImage.setVisibility(View.INVISIBLE);
        uploadedBreed.setVisibility(View.INVISIBLE);

        btn_Select.setText("Select To Upload Image");
        btn_Upload.setText("Analyse Image");

        canUpload = true;
        canAnalyse = true;
        newImageUploaded = false;

        btn_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canUpload) {
                    selectImage();
                } else {
                    Toast.makeText(ImageAnalysisActivity.this, "Uploading Current Image. Retry After Image is Uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckNetwork.isInternetAvailable(ImageAnalysisActivity.this)) {
                    if (image_id == null) {
                        Toast.makeText(ImageAnalysisActivity.this, "Upload Image before Analysing", Toast.LENGTH_LONG).show();
                    } else if (!newImageUploaded) {
                        Toast.makeText(ImageAnalysisActivity.this, "Image has Already been Analysed.", Toast.LENGTH_LONG).show();
                    } else if (!canUpload) {
                        Toast.makeText(ImageAnalysisActivity.this, "Upload in Progress. Please Wait", Toast.LENGTH_LONG).show();
                    } else if (canAnalyse) {
                        analyseImage();
                    } else {
                        Toast.makeText(ImageAnalysisActivity.this, "Analysis in Progress. Please Wait", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(ImageAnalysisActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });

        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.thedogapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");

        try {
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

        } catch (ActivityNotFoundException e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST) {

            if (resultCode == RESULT_OK) {

                try {
                    is = getContentResolver().openInputStream(data.getData());
                    fileUri = data.getData();
                    uploadedImage.setImageURI(fileUri);
                    uploadedImage.setVisibility(View.VISIBLE);
                    uploadedBreed.setVisibility(View.INVISIBLE);
                    newImageUploaded = true;
                    if(CheckNetwork.isInternetAvailable(ImageAnalysisActivity.this))
                    {
                        uploadImage(getBytes(is));

                    }
                    else
                    {
                        Toast.makeText(ImageAnalysisActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }


    private void uploadImage(byte[] imageBytes) {
        progressBar.setVisibility(View.VISIBLE);
        canUpload = false;

        int interval = 20000;

        CountDownTimer countDownTimer = new CountDownTimer(interval, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Toast.makeText(ImageAnalysisActivity.this, "Upload Failed. Image was too big, did not contain a Dog, was inappropriate, or the wrong file type", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                canUpload = true;
                uploadedImage.setVisibility(View.INVISIBLE);
            }
        }.start();

        mainInterface = retrofit2.create(MainInterface.class);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", "image.jpg", requestFile);

        Call<PhotoUploadModel> call = mainInterface.uploadImage(body);
        call.enqueue(new Callback<PhotoUploadModel>() {
            @Override
            public void onResponse(Call<PhotoUploadModel> call, Response<PhotoUploadModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    if (response.code() == 201) {
                        Toast.makeText(ImageAnalysisActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ImageAnalysisActivity.this, "Error : Image was too big, did not contain a Dog, was inappropriate, or the wrong file type.", Toast.LENGTH_LONG).show();
                    }
                    countDownTimer.cancel();
                    progressBar.setVisibility(View.INVISIBLE);
                    canUpload = true;
                    photoUploadModel = response.body();
                    image_id = photoUploadModel.getId();
                    Log.d(TAG, "image_id " + image_id);
                }
            }

            @Override
            public void onFailure(Call<PhotoUploadModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onResponse: " + t.getMessage());
                Toast.makeText(ImageAnalysisActivity.this, "Error Uploading Image. Try Again", Toast.LENGTH_LONG).show();
                canUpload = true;
                countDownTimer.cancel();
            }
        });
    }

    private void analyseImage() {
        canAnalyse = false;
        newImageUploaded = false;
        Call<ArrayList<ImageAnalysisModel>> call = mainInterface.analyseImage(image_id);
        call.enqueue(new Callback<ArrayList<ImageAnalysisModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ImageAnalysisModel>> call, Response<ArrayList<ImageAnalysisModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG1, "onResponse: " + response.code());
                    mImageAnalysisModel = response.body();
                    uploadedBreed.setTextColor(Color.WHITE);
                    uploadedBreed.setTextSize(30);
                    object = new Object();
                    if (mImageAnalysisModel.get(0).getLabels().get(0).getName().equals("Dog") || mImageAnalysisModel.get(0).getLabels().get(0).getName().equals("Pet")) {
                        Toast.makeText(ImageAnalysisActivity.this, "Unable to Determine Breed", Toast.LENGTH_LONG).show();
                    } else {
                        uploadedBreed.setText(mImageAnalysisModel.get(0).getLabels().get(0).getName());
                        uploadedBreed.setVisibility(View.VISIBLE);
                    }
                    canAnalyse = true;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ImageAnalysisModel>> call, Throwable t) {
                Log.d(TAG1, "onResponse: " + t.getMessage());
                Toast.makeText(ImageAnalysisActivity.this, "Error Analysing Image", Toast.LENGTH_LONG).show();
                canAnalyse = true;
            }
        });
    }
}
