package proyectofinal.helpme;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

public class MyIntro extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {

        //adding the three slides for introduction app you can ad as many you needed
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro1));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro2));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro3));

        // Show and Hide Skip and Done buttons
        showStatusBar(false);


        // Turn vibration on and set intensity
        // You will need to add VIBRATE permission in Manifest file
        setVibrate(true);
        setVibrateIntensity(30);

        //Add animation to the intro slider
        setDepthAnimation();


    }

    @Override
    public void onSkipPressed(android.support.v4.app.Fragment currentFragment) {
        // Do something here when users click or tap on Skip button.
        super.onSkipPressed(currentFragment);
        finish();
    }

    /*@Override
    public void onNextPressed(android.support.v4.app.Fragment currentFragment) {
        // Do something here when users click or tap on Next button.
        super.onNextPressed(currentFragment);
    }*/

    @Override
    public void onDonePressed(android.support.v4.app.Fragment currentFragment) {
        // Do something here when users click or tap tap on Done button.
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable android.support.v4.app.Fragment oldFragment, @Nullable android.support.v4.app.Fragment newFragment) {
        // Do something here when slide is changed
        super.onSlideChanged(oldFragment,newFragment);

    }
}
