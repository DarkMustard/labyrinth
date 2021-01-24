package com.example.labyrinth;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment0 extends Fragment {

    ImageView menustart;
    ImageView menuhowto;
    ImageView menuexit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        final ImageView menubg1 = (ImageView) view.findViewById(R.id.menu_bg1);
        final ImageView menubg2 = (ImageView) view.findViewById(R.id.menu_bg2);
        final Intent intent_game = new Intent(getActivity(), GameActivity.class);
        menustart = (ImageView)view.findViewById(R.id.menu_start);
        menuhowto = (ImageView)view.findViewById(R.id.menu_howto);
        menuexit = (ImageView)view.findViewById(R.id.menu_exit);


        menustart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_game);
            }
        });

        menuhowto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        menuexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(40000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = menubg1.getWidth();
                final float translationX = width * progress;
                menubg1.setTranslationX(translationX);
                menubg2.setTranslationX(translationX - width);
            }
        });
        animator.start();

        return view;
    }
}
