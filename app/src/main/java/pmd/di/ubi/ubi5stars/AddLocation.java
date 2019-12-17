package pmd.di.ubi.ubi5stars;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddLocation extends AppCompatActivity {

    private static final String TAG = "ubi5stars";

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etName;
    private EditText etDescription;
    private ImageView imageView;
    private EditText etImageName;

    private Uri mImageUri;

    private StorageReference storageRef;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        etName = findViewById(R.id.name);
        etDescription = findViewById(R.id.description);
        etImageName = findViewById(R.id.image_name);
        imageView = findViewById(R.id.image_view);

        storageRef = FirebaseStorage.getInstance().getReference("uploads");
        databaseRef = FirebaseDatabase.getInstance().getReference("locations");
    }

    public void uploadImage(View v) {
        openFileChooser();
    }

    public void addLocationToDB(View v) {
        uploadFile();
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

            Picasso.with(this).load(mImageUri).into(imageView);
        }
    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void addLocation() {

    }

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AddLocation.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();
                                    Location location = new Location(etName.getText().toString(), etDescription.getText().toString(), url);
                                    String uploadID = databaseRef.push().getKey();
                                    databaseRef.child(uploadID).setValue(location);
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddLocation.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            String url = etImageName.getText().toString();
            Location location = new Location(etName.getText().toString(), etDescription.getText().toString(), url);
            String uploadID = databaseRef.push().getKey();
            databaseRef.child(uploadID).setValue(location);
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
