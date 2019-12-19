package pmd.di.ubi.ubi5stars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class userProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void editUserData(View view) {

        EditText ETuser = findViewById(R.id.up_ins_email);
        EditText ETpass = findViewById(R.id.up_ins_password);
        EditText ETconf_pass = findViewById(R.id.up_password_confirmation);

        String user = ETuser.getText().toString();
        String pass = ETpass.getText().toString();
        String conf_pass = ETconf_pass.getText().toString();

        // TODO: IMPLEMENT
    }

    public void editUserImage(View view) {
        // TODO: IMPLEMENT
    }
}
