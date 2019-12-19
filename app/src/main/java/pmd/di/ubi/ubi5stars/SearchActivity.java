package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SearchActivity extends Activity {

    private final String locationsCollection = "locations";

    private TextView tvSearchBar;
    private LinearLayout searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tvSearchBar = findViewById(R.id.search_bar);
        searchResult = findViewById(R.id.search_result);
    }

    public void search(View v) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(locationsCollection);

        final String name = tvSearchBar.getText().toString();

        databaseRef.orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LocationCollection l = ds.getValue(LocationCollection.class);
                    if (l.getName().contains(name)) {
                        LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.search_result, null);

                        TextView tvLocation = ll.findViewById(R.id.location);
                        ImageView imageView = ll.findViewById(R.id.image_view);

                        tvLocation.setText(l.getName());

                        Picasso.with(SearchActivity.this)
                                .load(l.getImageURL())
                                .fit()
                                .centerCrop()
                                .into(imageView);

                        searchResult.addView(ll);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SearchActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
