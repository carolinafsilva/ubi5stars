package pmd.di.ubi.ubi5stars;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private EditText ETuser;
    private EditText ETpass;
    private EditText ETconf_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void uploadFoto(View v) {

    }

    public void editUserData(View view) {

        ETuser = findViewById(R.id.up_ins_email);
        ETpass = findViewById(R.id.up_ins_password);
        ETconf_pass = findViewById(R.id.up_password_confirmation);

        String user = ETuser.getText().toString();
        String pass = ETpass.getText().toString();
        String conf_pass = ETconf_pass.getText().toString();

        // TODO: IMPLEMENT
    }

    public void editUserImage(View view) {
        // TODO: IMPLEMENT
    }
}
