package com.example.weatherapp.ui.onboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.TaskExecutor;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.weatherapp.R;

import butterknife.BindView;

public class OnBoardAdapter extends PagerAdapter {
    private Context context;
    private String[] texts;
    private int[] pictures;

    public  OnBoardAdapter (Context context , String[] texts, int[] pictures ){
        this.context = context;
        this.texts = texts;
        this.pictures = pictures;
    }
    @Override
    public int getCount() {
        return texts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView boardText;
        ImageView boardImage;

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_onboard , container , false);

        boardText = itemView.findViewById(R.id.tvTitle);
        boardText.setText(texts[position]);

        boardImage = itemView.findViewById(R.id.imgOnBoard);
        boardImage.setImageResource(pictures[position]);

        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
