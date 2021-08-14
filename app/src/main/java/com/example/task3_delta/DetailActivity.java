package com.example.task3_delta;

import static com.example.task3_delta.MainActivity.EXTRA_BredFor;
import static com.example.task3_delta.MainActivity.EXTRA_BreedGroup;
import static com.example.task3_delta.MainActivity.EXTRA_BreedName;
import static com.example.task3_delta.MainActivity.EXTRA_HeightImperial;
import static com.example.task3_delta.MainActivity.EXTRA_HeightMetric;
import static com.example.task3_delta.MainActivity.EXTRA_LifeSpan;
import static com.example.task3_delta.MainActivity.EXTRA_Origin;
import static com.example.task3_delta.MainActivity.EXTRA_Temperament;
import static com.example.task3_delta.MainActivity.EXTRA_Url;
import static com.example.task3_delta.MainActivity.EXTRA_WeightImperial;
import static com.example.task3_delta.MainActivity.EXTRA_WeightMetric;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    ImageView imageViewDetail;
    TextView tv_BreedNameDetail,tv_LifeSpanDetail,tv_BredForDetail,tv_BreedGroupDetail,tv_HeightDetail,tv_WeightDetail,tv_TemperamentDetail,tv_Origin;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();

        imageViewDetail=findViewById(R.id.imageViewDetail);

        tv_BreedNameDetail=findViewById(R.id.tv_BreedNameDetail);
        tv_LifeSpanDetail=findViewById(R.id.tv_LifeSpanDetail);
        tv_BredForDetail=findViewById(R.id.tv_BredForDetail);
        tv_BreedGroupDetail=findViewById(R.id.tv_BreedGroupDetail);
        tv_HeightDetail=findViewById(R.id.tv_HeightDetail);
        tv_WeightDetail=findViewById(R.id.tv_WeightDetail);
        tv_TemperamentDetail=findViewById(R.id.tv_TemperamentDetail);
        tv_Origin=findViewById(R.id.tv_Origin);

        String imageUrl=intent.getStringExtra(EXTRA_Url);
        String breedName=intent.getStringExtra(EXTRA_BreedName).toUpperCase(Locale.ROOT);
        String lifeSpan="<font color=#AAAAAA>Life Span : </font>" + intent.getStringExtra(EXTRA_LifeSpan);
        String height="<font color=#AAAAAA>Height : </font>" + intent.getStringExtra(EXTRA_HeightImperial)+" inch / "+intent.getStringExtra(EXTRA_HeightMetric)+" cm";
        String weight="<font color=#AAAAAA>Weight : </font>" + intent.getStringExtra(EXTRA_WeightImperial)+" pound / "+intent.getStringExtra(EXTRA_WeightMetric)+" kg";
        String temperament="<font color=#AAAAAA>Temperament : </font>" + intent.getStringExtra(EXTRA_Temperament);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageViewDetail);
        tv_BreedNameDetail.setText(breedName);
        tv_LifeSpanDetail.setText(Html.fromHtml(lifeSpan, Html.FROM_HTML_MODE_LEGACY));
        tv_HeightDetail.setText(Html.fromHtml(height, Html.FROM_HTML_MODE_LEGACY));
        tv_WeightDetail.setText(Html.fromHtml(weight, Html.FROM_HTML_MODE_LEGACY));
        tv_TemperamentDetail.setText(Html.fromHtml(temperament, Html.FROM_HTML_MODE_LEGACY));

        if(!(intent.getStringExtra(EXTRA_Origin)==null || intent.getStringExtra(EXTRA_Origin).isEmpty())) {
            String origin = "<font color=#AAAAAA>Origin : </font>" + intent.getStringExtra(EXTRA_Origin);
            tv_Origin.setText(Html.fromHtml(origin, Html.FROM_HTML_MODE_LEGACY));
        }
        else{
            tv_Origin.setVisibility(View.INVISIBLE);
        }

        if(!(intent.getStringExtra(EXTRA_BreedGroup)==null || intent.getStringExtra(EXTRA_BreedGroup).isEmpty())) {
            String breedGroup="<font color=#AAAAAA>Breed Group : </font>"+ intent.getStringExtra(EXTRA_BreedGroup);
            tv_BreedGroupDetail.setText(Html.fromHtml(breedGroup, Html.FROM_HTML_MODE_LEGACY));
        }
        else{
            String breedGroup="<font color=#AAAAAA>Breed Group : </font>"+ "Not Available";
            tv_BreedGroupDetail.setText(Html.fromHtml(breedGroup,Html.FROM_HTML_MODE_LEGACY));
        }

        if(!(intent.getStringExtra(EXTRA_BredFor)==null || intent.getStringExtra(EXTRA_BredFor).isEmpty())) {
            String bredFor="<font color=#AAAAAA>Bred For : </font>" + intent.getStringExtra(EXTRA_BredFor);
            tv_BredForDetail.setText(Html.fromHtml(bredFor, Html.FROM_HTML_MODE_LEGACY));
        }
        else{
            String bredFor="<font color=#AAAAAA>Bred For : </font>" + "Not Available";
            tv_BredForDetail.setText(Html.fromHtml(bredFor, Html.FROM_HTML_MODE_LEGACY));
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle(breedName);
    }
}