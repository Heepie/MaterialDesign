package me.heepie.materialdesign;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import me.heepie.materialdesign.model.Item;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Heepie on 2018. 1. 3.
 */
public class DetailActivity extends AppCompatActivity {

    private FrameLayout background;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Init item and set theme
        initData();
        setContentView(R.layout.activity_detail);

        initView();
        initListener();
    }


    private void animateRevealShow(View viewRoot, @ColorRes int colorResId) {
        viewRoot.setBackgroundResource(colorResId);

        // Set lnk position
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
        CircleImageView imgView = findViewById(R.id.imgView);
        TextView txtView = findViewById(R.id.txtView);

        imgView.setImageResource(item.getColorResId());
        txtView.setText(item.getName());
    }

    private void initData() {
        item = getIntent().getParcelableExtra("model");
        setCustomTheme(item.getColorResId());
    }

    private void setCustomTheme(@ColorRes int colorResId) {
        @StyleRes int styleResId;

        switch (colorResId) {
            case R.color.theme_red_primary_dark:
                styleResId = R.style.MaterialAnimations_Red;
                break;
            case R.color.theme_blue_primary_dark:
                styleResId = R.style.MaterialAnimations_Blue;
                break;
            case R.color.theme_green_primary_dark:
                styleResId = R.style.MaterialAnimations_Green;
                break;
            case R.color.theme_yellow_primary_dark:
                styleResId = R.style.MaterialAnimations_Yellow;
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
                animateRevealShow(background, item.getColorResId());
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
