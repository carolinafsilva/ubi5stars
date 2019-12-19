package pmd.di.ubi.ubi5stars;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
    }

    public void getCurrCoord(View view) {
        Toast.makeText(getApplicationContext(), R.string.coming_soon, Toast.LENGTH_SHORT).show();
    }

    public void traceTrail(View view) {
        Toast.makeText(getApplicationContext(), R.string.coming_soon, Toast.LENGTH_SHORT).show();
    }

    public void museumTrail(View view) {
        Toast.makeText(getApplicationContext(), R.string.coming_soon, Toast.LENGTH_SHORT).show();
    }

    public void urbanartTrail(View view) {
        Toast.makeText(getApplicationContext(), R.string.coming_soon, Toast.LENGTH_SHORT).show();
    }

    public void gardenTrail(View view) {
        Toast.makeText(getApplicationContext(), R.string.coming_soon, Toast.LENGTH_SHORT).show();
    }

    public void nightlifeTrail(View view) {
        Toast.makeText(getApplicationContext(), R.string.coming_soon, Toast.LENGTH_SHORT).show();
    }
}
