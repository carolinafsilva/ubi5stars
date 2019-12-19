package pmd.di.ubi.ubi5stars;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserProfileActivity extends AppCompatActivity {

    FirebaseUser user;

    private ImageView IVphoto;

    private TextView TVuser;
    private TextView TVemail;

    private EditText ETuser;
    private EditText ETpass;
    private EditText ETconf_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            TVuser = findViewById(R.id.up_username);
            TVemail = findViewById(R.id.up_emailaddr);

            TVuser.setText(user.getDisplayName());
            TVemail.setText(user.getEmail());
        }
    }

    public void uploadFoto(View v) {

        IVphoto = findViewById(R.id.photo);
        
    }

    public void editUserData(View view) {



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
                                    Toast.makeText(getApplicationContext(), "Updated username", Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(getApplicationContext(), "Updated password", Toast.LENGTH_LONG).show();
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
