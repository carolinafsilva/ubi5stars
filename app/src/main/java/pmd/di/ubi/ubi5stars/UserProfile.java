package pmd.di.ubi.ubi5stars;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {

    private FirebaseUser FBuser;
    private Button Bupload;
    private EditText ETuser;
    private EditText ETpass;
    private EditText ETconf_pass;
    private TextView TVemail;
    private TextView TVemailadrr;
    private Button Bedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ETuser = findViewById(R.id.up_ins_email);
        ETpass = findViewById(R.id.up_ins_password);
        ETconf_pass = findViewById(R.id.up_password_confirmation);
        Bupload = findViewById(R.id.up_upload);
        TVemail = findViewById(R.id.up_email);
        TVemailadrr = findViewById(R.id.up_emailaddr);
        Bedit = findViewById(R.id.up_edit);

        FBuser = FirebaseAuth.getInstance().getCurrentUser();

        if(FBuser == null) {
            ETuser.setVisibility(View.INVISIBLE);
            ETpass.setVisibility(View.INVISIBLE);
            ETconf_pass.setVisibility(View.INVISIBLE);
            Bupload.setEnabled(false);
            TVemail.setText(R.string.empty_user);
            TVemailadrr.setVisibility(View.INVISIBLE);
            Bedit.setEnabled(false);
        }
    }

    public void editUserData(View view) {

        String username = ETuser.getText().toString();
        String pass = ETpass.getText().toString();
        String conf_pass = ETconf_pass.getText().toString();
    }

    public void editUserImage(View view) {
        // TODO: IMPLEMENT
    }
}
