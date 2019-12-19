package pmd.di.ubi.ubi5stars;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView IVphoto;

    private EditText ETuser;
    private EditText ETpass;
    private EditText ETconf_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void uploadFoto(View v) {

        IVphoto = findViewById(R.id.photo);
        
    }

    public void editUserData(View view) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            ETuser = findViewById(R.id.up_ins_email);
            ETpass = findViewById(R.id.up_ins_password);
            ETconf_pass = findViewById(R.id.up_password_confirmation);

            String username = ETuser.getText().toString();

            if (!username.equals("")) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                }
                            }
                        });
            }

            String pass = ETpass.getText().toString();
            String conf_pass = ETconf_pass.getText().toString();

            if (!pass.equals("") && pass.equals(conf_pass)) {
                user.updatePassword(pass)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                }
                            }
                        });
            }
        }
    }

    public void editUserImage(View view) {
        // TODO: IMPLEMENT
    }
}
