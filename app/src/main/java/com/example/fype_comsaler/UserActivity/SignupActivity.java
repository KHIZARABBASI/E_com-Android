package com.example.fype_comsaler.UserActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fype_comsaler.Models.UserModel;
import com.example.fype_comsaler.R;
import com.example.fype_comsaler.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignupActivity extends AppCompatActivity {
    private final int GALLERY_REQUEST_CODE = 1001;
    ActivitySignupBinding binding;
    FirebaseDatabase db;
    Uri  filePath;
    DatabaseReference dbReferance;
    String name, email, password, username, typeAccount;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainSign), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.registerInputName.getText().toString();
                email = binding.registerInputEmail.getText().toString();
                password = binding.registerInputPassword.getText().toString();
                username = binding.registerInputUserName.getText().toString();
                typeAccount = binding.registerInputAccType.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || username.isEmpty() || typeAccount.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    createAccount(name, email, password, username, typeAccount);


                    UserModel userModel = new UserModel(username, name, email, typeAccount);
                    db = FirebaseDatabase.getInstance();
                    dbReferance = db.getReference("users");
                    dbReferance.child(username).setValue(userModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
//                                    binding.registerInputUserName.setText("");
//                                    binding.registerInputName.setText("");
//                                    binding.registerInputPassword.setText("");
//                                    binding.registerInputEmail.setText("");
//                                    binding.registerInputUserName.setText("");
                                    Toast.makeText(SignupActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                                }
                            });


                }


//                addDatabase(name, email, password, username, typeAccount);


            }
        });
        binding.gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.signupPPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent();
                iGallery.setAction(Intent.ACTION_GET_CONTENT);
                iGallery.setType("image/*");
                startActivityForResult(iGallery, GALLERY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && data!=null) {
            filePath = data.getData();
            binding.profileP.setImageURI(filePath);
        }

    }

    private void addDatabase(String name, String email, String password, String username, String typeAccount) {

        UserModel userModel = new UserModel(username, name, email, typeAccount);
        db = FirebaseDatabase.getInstance();
        dbReferance = db.getReference("user");
        dbReferance.child(username).setValue(userModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        Toast.makeText(SignupActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void createAccount(String name, String email, String password, String username, String typeAccount) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            ProgressBar bar = new ProgressBar(this);

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Creating");
            progressDialog.setMessage("Account");
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email.trim(), password.trim())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {


                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).updateProfile(profileChangeRequest);


                            progressDialog.cancel();
                            Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
//                            binding.registerInputName.setText("");
//                            binding.registerInputEmail.setText("");
//                            binding.registerInputPassword.setText("");
//                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                        }
                    });
        }
    }
}
