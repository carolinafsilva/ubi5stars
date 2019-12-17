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

public class LoginActivity extends Activity {

    FirebaseAuth mAuth;
    EditText emailET, passwordET;
    int REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.l_email);
        passwordET = findViewById(R.id.l_password);
    }

    public void showMenu(View v) {
        startActivity(new Intent(this, MenuActivity.class));
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
                            Toast.makeText(getApplicationContext(), "LoginActivity successful!", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(LoginActivity.this, MapActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "LoginActivity failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void startRegistry(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(i, REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE)
            if (resultCode == RESULT_OK) {

                // Colocar diretamente os dados do login nos editTexts

                // data.getStringExtra ??
                /*emailET =
                passwordET =*/
            }
    }
}
