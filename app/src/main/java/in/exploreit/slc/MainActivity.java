package in.exploreit.slc;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AnticipateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSplashScreen();
        setContentView(R.layout.activity_main);
        checkIfLoggedIn();
    }

    private void checkIfLoggedIn() {
        boolean isLoggedIn = FirebaseAuth.getInstance().getCurrentUser() != null;
        navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_graph);
        if(isLoggedIn) {
            navGraph.setStartDestination(R.id.homeFragment);
        } else navGraph.setStartDestination(R.id.commonLoginFragment);
        navController.setGraph(navGraph);
    }

    private void setupSplashScreen() {
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
    }

    public NavController getNavController() { return navController; }
}