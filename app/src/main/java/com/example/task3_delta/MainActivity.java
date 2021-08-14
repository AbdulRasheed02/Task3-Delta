package com.example.task3_delta;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickListener {
    public static final String EXTRA_Url="imageUrl";
    public static final String EXTRA_BreedName="breedName";
    public static final String EXTRA_LifeSpan="lifeSpan";
    public static final String EXTRA_BredFor="bredFor";
    public static final String EXTRA_BreedGroup="breedGroup";
    public static final String EXTRA_HeightImperial="heightImperial";
    public static final String EXTRA_HeightMetric="heightMetric";
    public static final String EXTRA_WeightImperial="weightImperial";
    public static final String EXTRA_WeightMetric="weightMetric";
    public static final String EXTRA_Temperament="temperament";
    public static final String EXTRA_Origin="origin";
    private static final String TAG = "Hello";

    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private ArrayList<MainItem> mMainList;
    MainInterface mainInterface;

    TextView tv_identifyBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("PAWSOME");

        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMainList=new ArrayList<>();

        tv_identifyBreed=findViewById(R.id.tv_identifyBreed);

        tv_identifyBreed.setText("FIND DOG BREED");

        tv_identifyBreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageAnalysis=new Intent(MainActivity.this,ImageAnalysisActivity.class);
                startActivity(imageAnalysis);
            }
        });

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.thedogapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mainInterface=retrofit.create(MainInterface.class);

        if(CheckNetwork.isInternetAvailable(MainActivity.this))
        {
            getMainList();
        }
        else
        {
            Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }

    }

    private void getMainList(){
        Call<ArrayList<MainItem>> call= mainInterface.getMainItem(null,null);

        call.enqueue(new Callback<ArrayList<MainItem>>() {
            @Override
            public void onResponse(Call<ArrayList<MainItem>> call, Response<ArrayList<MainItem>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                mMainList=response.body();
                Log.d(TAG, "onResponse: Hello");

                mMainAdapter = new MainAdapter(MainActivity.this, mMainList);
                mRecyclerView.setAdapter(mMainAdapter);
                mMainAdapter.setOnItemClickListener(MainActivity.this);
            }

            @Override
            public void onFailure(Call<ArrayList<MainItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent=new Intent(this,DetailActivity.class);
        MainItem clickedItem=mMainList.get(position);

            detailIntent.putExtra(EXTRA_Url, clickedItem.getImageDog().getUrl());
            detailIntent.putExtra(EXTRA_BreedName, clickedItem.getName());
            detailIntent.putExtra(EXTRA_LifeSpan, clickedItem.getLifeSpan());
            detailIntent.putExtra(EXTRA_BredFor, clickedItem.getBredFor());
            detailIntent.putExtra(EXTRA_BreedGroup, clickedItem.getBreedGroup());
            detailIntent.putExtra(EXTRA_HeightImperial, clickedItem.getHeight().getImperial());
            detailIntent.putExtra(EXTRA_HeightMetric, clickedItem.getHeight().getMetric());
            detailIntent.putExtra(EXTRA_WeightImperial, clickedItem.getWeight().getImperial());
            detailIntent.putExtra(EXTRA_WeightMetric, clickedItem.getWeight().getMetric());
            detailIntent.putExtra(EXTRA_Temperament, clickedItem.getTemperament());
            detailIntent.putExtra(EXTRA_Origin, clickedItem.getOrigin());
            startActivity(detailIntent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}