package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TrailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
    }

    // Ou apenas fazer um método e depois faz-se um switch dependendo do ID da view

    public void getCurrCoord(View view) {
    }

    public void traceTrail(View view) {
    }

    public void museumTrail(View view) {
    }

    public void urbanartTrail(View view) {
    }

    public void gardenTrail(View view) {
    }

    public void nightlifeTrail(View view) {
    }
}
