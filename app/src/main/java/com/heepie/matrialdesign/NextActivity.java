package com.heepie.matrialdesign;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.heepie.matrialdesign.model.Item;

import de.hdodenhof.circleimageview.CircleImageView;

public class NextActivity extends AppCompatActivity {

    private CircleImageView imgView;
    private FrameLayout background;
    private TextView txtView;
    private Item mItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTheme 메소드 설정을 위해 onCreate 후 바로 실행
        initData();
        setContentView(R.layout.activity_next);

        initView();
        initListener();
    }


    private void animateRevealShow(View viewRoot, @ColorRes int colorResId) {
        viewRoot.setBackgroundResource(colorResId);
        // 시작 위치 설정 x,y
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(1000);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.start();
    }

    private void initView() {
        background = findViewById(R.id.background);
        imgView = findViewById(R.id.imgView);
        txtView = findViewById(R.id.txtView);

        imgView.setImageResource(mItem.getColorResId());
        txtView.setText(mItem.getName());
    }

    private void initData() {
        mItem = getIntent().getParcelableExtra("model");
        setCustomTheme(mItem.getColorResId());
    }

    private void setCustomTheme(@ColorRes int colorResId) {
        @StyleRes int styleResId;

        switch (colorResId) {
            case R.color.theme_yellow_primary_dark:
                styleResId = R.style.MaterialAnimations_Yellow;
                break;

            case R.color.theme_red_primary_dark:
                styleResId = R.style.MaterialAnimations_Red;
                break;

            default:
                styleResId = R.style.MaterialAnimations;
                break;
        }

        setTheme(styleResId);
    }

    private void initListener() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealShow(background, mItem.getColorResId());
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }
}
