package pmd.di.ubi.ubi5stars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void hideMenu(View v) {
        Toast.makeText(getApplicationContext(), getString(R.string.menu_ee), Toast.LENGTH_SHORT).show();
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

    public void addLocal(View v) {
        startActivity(new Intent(this, AddLocation.class));
    }

    public void settings(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void logout(View view) {
    }

    public void ranking(View view) {
    }
}
