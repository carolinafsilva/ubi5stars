package pmd.di.ubi.ubi5stars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void hideMenu(View v) {
        super.finish();
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

    public void search(View v) {
        Intent intent = new Intent(MenuActivity.this, AddLocation.class);
        startActivity(intent);
    }

    public void trail(View v) {
        Intent intent = new Intent(MenuActivity.this, ShowLocation.class);
        startActivity(intent);
    }

    public void settings(View v) {
        Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
