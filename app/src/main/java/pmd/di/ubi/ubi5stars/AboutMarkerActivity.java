package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
    private EditText etCommentText;
    private TextView tvName;
    private TextView tvDescription;
    private ImageView imageView;
    private Button bSubmit;
    private LinearLayout comment_section;


    private static String commentsCollection = "comments";
    private static String locationsCollection = "locations";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markerinfo);

        Intent i = getIntent();

        String name = i.getStringExtra("locationName");

        getLocation(name);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int c = display.widthPixels;
        int l = display.heightPixels;

        //getWindow().setLayout((int) (c * 0.8), (int) (l * 0.8));

        etCommentText = findViewById(R.id.comment_text);
        tvName = findViewById(R.id.name);
        tvDescription = findViewById(R.id.description);
        imageView = findViewById(R.id.image_view);
        ratingBar = findViewById(R.id.rating);
        bSubmit = findViewById(R.id.submit_btn);
        comment_section = findViewById(R.id.comment_section);

        getComments(name);
    }

    public void onExitClicked(View v) {
        super.finish();
    }

    public void submitComment() {
        String commentText = etCommentText.getText().toString();
        float rating = ratingBar.getRating();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String username;
        if (user == null) {
            username = "anonymous";
        } else {
            username = user.getEmail();
        }
        String location = tvName.getText().toString();
        Comment comment = new Comment(commentText, username, location, rating);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(commentsCollection);
        String uploadID = databaseRef.push().getKey();
        databaseRef.child(uploadID).setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AboutMarkerActivity.this, "Comentário Submetido com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.finish();
    }


    public void getLocation(String name) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(locationsCollection);
        databaseRef.orderByChild("name").equalTo(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot ds = dataSnapshot.getChildren().iterator().next();
                final Location l = ds.getValue(Location.class);
                tvName.setText(l.getName());
                tvDescription.setText(l.getDescription());
                Picasso.with(AboutMarkerActivity.this)
                        .load(l.getImageURL())
                        .fit()
                        .centerCrop()
                        .into(imageView);

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
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Comment c = ds.getValue(Comment.class);
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

                    comment_section.addView(ll);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
