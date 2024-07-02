package com.example.photoframeapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button prevButton, nextButton;

    private int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        // Set initial image
        imageView.setImageResource(images[currentIndex]);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex - 1 + images.length) % images.length;
                changeImageWithAnimation();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % images.length;
                changeImageWithAnimation();
            }
        });
    }

    private void changeImageWithAnimation() {
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(100);
        fadeOut.setAnimationListener(new AlphaAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {
            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                imageView.setImageResource(images[currentIndex]);
                AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                fadeIn.setDuration(100);
                imageView.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {
            }
        });
        imageView.startAnimation(fadeOut);
    }
}
