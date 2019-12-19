package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
        startActivity(new Intent(this, ShowLocation.class));
    }

    public void settings(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void logout(View view) {
    }

    public void ranking(View view) {
        startActivity(new Intent(this, RankingActivity.class));
    }

    public void toProfile(View view) {
        startActivity(new Intent(this, userProfile.class));
    }
}
