package in.exploreit.slc;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AnticipateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import in.exploreit.slc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this).setOnExitAnimationListener(
                splashScreenViewProvider -> {
                    ObjectAnimator fade = ObjectAnimator.ofFloat(
                            splashScreenViewProvider.getIconView(),
                            "alpha",
                            1.0f,
                            0.0f
                    );
                    fade.setInterpolator(new AnticipateInterpolator());
                    fade.setDuration(500L);
                    fade.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animation) {splashScreenViewProvider.remove();}
                        @Override public void onAnimationStart(Animator animation) { }
                        @Override public void onAnimationCancel(Animator animation) { }
                        @Override public void onAnimationRepeat(Animator animation) { }
                    });
                    fade.start();
                }
        );
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.meow.setText(R.string.app_name);
    }
}