package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RankingActivity extends Activity {

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

        databaseRef.orderByChild("rating").limitToFirst(5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LocationCollection l = ds.getValue(LocationCollection.class);
                    LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.ranking_line, null);

                    ImageView imageView = ll.findViewById(R.id.image_view);
                    TextView tvLocation = ll.findViewById(R.id.tv_location_name);

                    Picasso.with(RankingActivity.this)
                            .load(l.getImageURL())
                            .fit()
                            .centerCrop()
                            .into(imageView);

                    tvLocation.setText(l.getName());


                    llRanking.addView(ll);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
