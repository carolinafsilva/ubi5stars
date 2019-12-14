package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class aboutMarker extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markerinfo);

        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.fivestars);
        Button submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(ratingRatingBar.getRating());
            }
        });
    }

    public void goBack(View v){
        Intent intent = new Intent(aboutMarker.this,Map.class);
        startActivity(intent);
    }


}
