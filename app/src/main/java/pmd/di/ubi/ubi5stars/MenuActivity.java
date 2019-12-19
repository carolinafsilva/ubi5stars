package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MenuActivity extends Activity {

    FirebaseAuth mAuth;
    FirebaseUser user;

    private LinearLayout llLogged;
    private LinearLayout llNotLogged;
    private ImageView imageView;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        llLogged = findViewById(R.id.ll_logged);
        llNotLogged = findViewById(R.id.ll_not_loged);

        mAuth = FirebaseAuth.getInstance();

        updateUI();
    }

    public void updateUI() {
        imageView = findViewById(R.id.profile_picture);
        tvName = findViewById(R.id.profile_name);

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    llLogged.setVisibility(View.VISIBLE);
                    llNotLogged.setVisibility(View.INVISIBLE);
                    if (user.getPhotoUrl() != null) {
                        Picasso.with(MenuActivity.this)
                                .load(user.getPhotoUrl())
                                .fit()
                                .centerCrop()
                                .into(imageView);
                    }
                    tvName.setText(user.getDisplayName());
                } else {
                    llLogged.setVisibility(View.INVISIBLE);
                    llNotLogged.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void login(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(new Intent(this, LoginActivity.class));
        super.finish();
    }

    public void register(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void map(View v) {
        startActivity(new Intent(this, MapActivity.class));
        super.finish();
    }

    public void search(View v) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void trail(View v) {
        startActivity(new Intent(this, TrailsActivity.class));
    }

    public void settings(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void logout(View v) {
        mAuth.signOut();
        super.finish();
    }

    public void ranking(View v) {
        startActivity(new Intent(this, RankingActivity.class));
    }

    public void toProfile(View v) {
        startActivity(new Intent(this, UserProfileActivity.class));
        super.finish();
    }
}
