package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class aboutMarker extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markerinfo);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int c = display.widthPixels;
        int l = display.heightPixels;

        getWindow().setLayout((int) (c * 0.8), (int) (l * 0.8));

        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.fivestars);
        Button submitButton = (Button) findViewById(R.id.submit);
        final EditText comment  = (EditText)findViewById(R.id.editText1);
        TextView description = (TextView)findViewById(R.id.descricao);

        submitButton.setOnClickListener(new View.OnClickListener() {    // Listener for submit button
            @Override
            public void onClick(View v) {   // Change what's inside this method in order to save rating and comment on the database
                System.out.println(ratingRatingBar.getRating());    // Get Ranking (5 stars)
                System.out.println(comment.getText());  // Get Comment
            }
        });

        ImageView image = (ImageView) findViewById(R.id.imageView1);    // Put image on imageView
        image.setImageResource(R.drawable.reitoria);    // Change R.drawable.reitoria to the image directory (database)

        description.setText("Insert Description here"); // Insert Description

    }



    @Override
    public void onBackPressed() {
        //Toast.makeText(getBaseContext(), "ha ha tens de usar o butão lá em cima!", Toast.LENGTH_SHORT).show();
        super.finish();
    }



}
