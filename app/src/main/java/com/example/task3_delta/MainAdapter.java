package com.example.task3_delta;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private Context mContext;
    private ArrayList<MainItem> mMainItem;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public MainAdapter(Context context,ArrayList<MainItem> mainItem){
        mContext=context;
        mMainItem=mainItem;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.main_item,parent,false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        MainItem currentItem=mMainItem.get(position);

        String imageUrl=currentItem.getImageDog().getUrl();
        String breedName = currentItem.getName();

        holder.mTextViewBreedName.setText(breedName);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMainItem.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewBreedName;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageView);
            mTextViewBreedName=itemView.findViewById(R.id.tv_BreedName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAbsoluteAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }

                    }
                }
            });
        }
    }
}
