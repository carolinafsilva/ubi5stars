package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends Activity {

    private final int secs = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent i;
                if (user == null) {
                    i = new Intent(WelcomeActivity.this, LoginActivity.class);

                } else {
                    i = new Intent(WelcomeActivity.this, MapActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, secs);
    }
}
