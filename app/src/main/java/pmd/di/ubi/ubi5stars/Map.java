package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Map extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

    }


    public void showMenu(View v) {
        startActivity(new Intent(Map.this, Menu.class));
    }

    public void search(View v) {
        // TODO: implement
    }
}
