package pmd.di.ubi.ubi5stars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText emailET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.l_email);
        passwordET = findViewById(R.id.l_password);

    }

    public void showMenu(View v) {
        startActivity(new Intent(this, MapActivity.class));
        super.finish();
    }

    public void login(View v) {
        String email, password;
        email = emailET.getText().toString();
        password = passwordET.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.successful_login, Toast.LENGTH_LONG).show();

                            Intent i = new Intent(LoginActivity.this, MapActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.failed_login, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void startRegistry(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.finish();
    }
}
