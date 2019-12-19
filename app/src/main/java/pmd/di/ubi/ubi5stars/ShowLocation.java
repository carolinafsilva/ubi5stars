package pmd.di.ubi.ubi5stars;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ShowLocation extends AppCompatActivity {

    private TextView tvName;
    private TextView tvDescription;
    private ImageView imageView;
    private Uri mImageUri;

    private StorageReference storageRef;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        tvName = findViewById(R.id.name);
        tvDescription = findViewById(R.id.description);
        imageView = findViewById(R.id.image_view);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("locations");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LocationCollection locationCollection = ds.getValue(LocationCollection.class);
                    tvName.setText(locationCollection.getName());
                    tvDescription.setText(locationCollection.getDescription());
                    System.out.println(locationCollection.getImageURL());
                    Picasso.with(ShowLocation.this)
                            .load(locationCollection.getImageURL())
                            .fit()
                            .centerCrop()
                            .into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShowLocation.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
