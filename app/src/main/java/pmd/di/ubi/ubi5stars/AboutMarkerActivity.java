package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

public class AboutMarkerActivity extends Activity {

    private RatingBar ratingBar;
    private RatingBar avgRating;
    private EditText etCommentText;
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvNumRatings;
    private ImageView imageView;
    private Button bSubmit;
    private LinearLayout comment_section;


    private static String commentsCollection = "comments";
    private static String locationsCollection = "locations";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markerinfo);

        etCommentText = findViewById(R.id.comment_text);
        tvName = findViewById(R.id.name);
        tvDescription = findViewById(R.id.description);
        imageView = findViewById(R.id.image_view);
        ratingBar = findViewById(R.id.rating);
        bSubmit = findViewById(R.id.submit_btn);
        comment_section = findViewById(R.id.comment_section);
        tvNumRatings = findViewById(R.id.tv_num_ratings);
        avgRating = findViewById(R.id.avg_rating);

        Intent i = getIntent();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            ratingBar.setIsIndicator(true);
            etCommentText.setEnabled(false);
        }

        String name = i.getStringExtra("locationName");

        getLocation(name);
        getComments(name);
    }

    public void onExitClicked(View v) {
        super.finish();
    }

    public void submitComment() {
        String commentText = etCommentText.getText().toString();
        final float rating = ratingBar.getRating();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String username;
        if (user == null) {
            Toast.makeText(this, "Log in to comment", Toast.LENGTH_SHORT).show();
        } else {
            username = user.getDisplayName();

            String location = tvName.getText().toString();
            CommentCollection commentCollection = new CommentCollection(commentText, username, location, rating);

            DatabaseReference locationRef = FirebaseDatabase.getInstance().getReference(locationsCollection);
            locationRef.orderByChild("name").equalTo(location).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    DataSnapshot ds = dataSnapshot.getChildren().iterator().next();
                    LocationCollection l = ds.getValue(LocationCollection.class);
                    float sum = l.getSum() + rating;
                    float total = l.getTotal() + 1;
                    float rating = sum / total;
                    DatabaseReference db = ds.getRef();
                    db.child("sum").setValue(sum);
                    db.child("total").setValue(total);
                    db.child("rating").setValue(rating);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(commentsCollection);
            String uploadID = databaseRef.push().getKey();
            databaseRef.child(uploadID).setValue(commentCollection).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    etCommentText.setText("");
                    ratingBar.setRating(0);
                    Toast.makeText(AboutMarkerActivity.this, R.string.successful_comment, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void getLocation(String name) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(locationsCollection);
        databaseRef.orderByChild("name").equalTo(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot ds = dataSnapshot.getChildren().iterator().next();
                final LocationCollection l = ds.getValue(LocationCollection.class);
                tvName.setText(l.getName());
                tvDescription.setText(l.getDescription());
                Picasso.with(AboutMarkerActivity.this)
                        .load(l.getImageURL())
                        .fit()
                        .centerCrop()
                        .into(imageView);
                avgRating.setRating(l.getRating());
                tvNumRatings.setText("(" + ((int) l.getTotal()) + " reviews)");
                bSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submitComment();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AboutMarkerActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getComments(String location) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(commentsCollection);
        databaseRef.orderByChild("location").equalTo(location).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comment_section.removeAllViews();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    CommentCollection c = ds.getValue(CommentCollection.class);
                    SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
                    String date = formater.format(c.getDate());
                    LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.comment_section, null);


                    TextView tvUsername = ll.findViewById(R.id.username);
                    TextView tvDate = ll.findViewById(R.id.date);
                    RatingBar ratingBar = ll.findViewById(R.id.rating);
                    TextView tvCommentText = ll.findViewById(R.id.comment_text);

                    tvUsername.setText(c.getUsername());
                    tvDate.setText(date);
                    ratingBar.setRating(c.getRating());
                    tvCommentText.setText(c.getText());

                    comment_section.addView(ll, 0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AboutMarkerActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
