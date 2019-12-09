package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends Activity {

    private FirebaseAuth mAuth;
    private EditText emailET;
    private EditText passwordET;
    private EditText passwordConfirmationET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        passwordConfirmationET = findViewById(R.id.password_confirmation);
    }

    public void showMenu(View v) {
        startActivity(new Intent(this, Menu.class));
    }

    public void register(View v) {
        String email, password;
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        if (password.equals(passwordConfirmationET.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                                Intent i = new Intent(Register.this, Login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

}
