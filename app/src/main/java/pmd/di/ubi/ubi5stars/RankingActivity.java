package pmd.di.ubi.ubi5stars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RankingActivity extends AppCompatActivity {

    private final String locationsCollection = "locations";

    private LinearLayout llRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        llRanking = findViewById(R.id.top_locations);
        getTop();
    }

    public void getTop() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(locationsCollection);

        databaseRef.orderByChild("rating").limitToLast(5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                llRanking.removeAllViews();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LocationCollection l = ds.getValue(LocationCollection.class);
                    LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.ranking_line, null);

                    ImageView imageView = ll.findViewById(R.id.image_view);
                    TextView tvLocation = ll.findViewById(R.id.tv_location_name);
                    RatingBar ratingBar = ll.findViewById(R.id.avg_rating);
                    Picasso.with(RankingActivity.this)
                            .load(l.getImageURL())
                            .fit()
                            .centerCrop()
                            .into(imageView);

                    tvLocation.setText(l.getName());
                    ratingBar.setRating(l.getRating());

                    llRanking.addView(ll, 0);

                    ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView location = v.findViewById(R.id.tv_location_name);
                            Intent i = new Intent(RankingActivity.this, AboutMarkerActivity.class);
                            i.putExtra("locationName", location.getText().toString());
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
