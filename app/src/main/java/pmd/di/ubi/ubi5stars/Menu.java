package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void hideMenu(View v) {
        super.finish();
    }

    public void login(View v) {
        startActivity(new Intent(this, Login.class));
    }

    public void register(View v) {
        startActivity(new Intent(this, Register.class));
    }

    public void map(View v) {
        startActivity(new Intent(this, Map.class));
    }

    public void search(View v) {
        Intent intent = new Intent(Menu.this,Search.class);
        startActivity(intent);
    }

    public void trail(View v) {
    }

    public void settings(View v) {
        Intent intent = new Intent(Menu.this,Settings.class);
        startActivity(intent);
    }
}
