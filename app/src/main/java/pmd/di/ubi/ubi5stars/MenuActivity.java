package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends Activity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View v) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void register(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void map(View v) {
        startActivity(new Intent(this, MapActivity.class));
    }

    public void search(View v) { startActivity(new Intent(this, SearchActivity.class)); }

    public void trail(View v) {
        startActivity(new Intent(this, TrailsActivity.class));
    }

    public void settings(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void logout(View v) {
        mAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void ranking(View v) {
        startActivity(new Intent(this, RankingActivity.class));
    }

    public void toProfile(View v) {
        startActivity(new Intent(this, UserProfile.class));
    }
}
