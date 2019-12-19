package pmd.di.ubi.ubi5stars;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private int PICK_IMAGE_REQUEST = 1;

    private ImageView mImageView;
    private Uri mImageUri;

    private Button Bupload;
    private Button Bedit;

    private TextView TVuser;
    private TextView TVemail;

    private EditText etUser;
    private EditText etPass;
    private EditText etConf_pass;

    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        Bupload = findViewById(R.id.up_upload);
        Bedit = findViewById(R.id.up_edit);

        TVuser = findViewById(R.id.up_username);
        TVemail = findViewById(R.id.up_emailaddr);

        etUser = findViewById(R.id.up_ins_email);
        etPass = findViewById(R.id.up_ins_password);
        etConf_pass = findViewById(R.id.up_password_confirmation);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        mImageView = findViewById(R.id.photo);

        if (user != null) {
            TVuser = findViewById(R.id.up_username);
            TVemail = findViewById(R.id.up_emailaddr);

            TVuser.setText(user.getDisplayName());
            TVemail.setText(user.getEmail());

            if (user.getPhotoUrl() != null) {
                Picasso.with(UserProfileActivity.this)
                        .load(user.getPhotoUrl())
                        .fit()
                        .centerCrop()
                        .into(mImageView);
            }
        } else {

            Bupload.setEnabled(false);
            Bedit.setEnabled(false);
            TVuser.setText(R.string.empty_user);
            TVemail.setVisibility(View.INVISIBLE);
            etUser.setVisibility(View.INVISIBLE);
            etPass.setVisibility(View.INVISIBLE);
            etConf_pass.setVisibility(View.INVISIBLE);
        }

        storageRef = FirebaseStorage.getInstance().getReference("profile_pictures");
    }


    public void editUserData(View view) {

        if (user != null) {

            String username = etUser.getText().toString();

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
                                    TVuser.setText(user.getDisplayName());
                                }
                            }
                        });
            }

            String pass = etPass.getText().toString();
            String conf_pass = etConf_pass.getText().toString();

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


    public void uploadFoto(View v) {
        openFileChooser();
    }


    private void openFileChooser() {
        Intent i = new Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);

            if (mImageUri != null) {
                final StorageReference fileReference = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

                fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UserProfileActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                        fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setPhotoUri(uri)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getApplicationContext(), "Updated profile picture", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            }
                        });

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
