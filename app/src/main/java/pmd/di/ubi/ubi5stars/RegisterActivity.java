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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailET;
    private EditText passwordET;
    private EditText passwordConfirmationET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.r_email);
        passwordET = findViewById(R.id.r_password);
        passwordConfirmationET = findViewById(R.id.r_password_confirmation);
    }

    public void showMenu(View v) {
        startActivity(new Intent(this, MenuActivity.class));
    }

    public void register(View v) {
        final String email, password;
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        if (password.equals(passwordConfirmationET.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), R.string.successful_register, Toast.LENGTH_LONG).show();

                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);

                                // TODO: Isto pode fluir assim? Vejam no Login o onActivityResult

                                i.putExtra("email", email);
                                i.putExtra("password", password);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                setResult(RESULT_OK, i);

                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), R.string.failed_register, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

}
