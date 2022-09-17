package lk.proj.tourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import lk.proj.tourapp.dto.User;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    Button btnLogin;
    EditText txtName;
    EditText txtContactNo;
    EditText txtEmail;
    EditText txtPassword;
    TextView lblGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnLogin = findViewById(R.id.btnRegister);
        txtName = findViewById(R.id.txtRName);
        txtContactNo = findViewById(R.id.txtRContactNo);
        txtEmail = findViewById(R.id.txtREmail);
        txtPassword = findViewById(R.id.txtRPwd);
        lblGoToLogin = findViewById(R.id.lblGoToLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = txtName.getText().toString();
                String contactNo = txtContactNo.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Name is Required",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (contactNo.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Contact No is Required",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (contactNo.length()!=10) {
                    Toast.makeText(RegisterActivity.this, "Invalid Contact No",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.isEmpty()) {
//                    Toast.makeText(activity_register.this, "Email is Required",
//                            Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Alert")
                            .setMessage("Email is Required")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                    return;
                }

                if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    Toast.makeText(RegisterActivity.this, "Invalid Email",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
//                    Toast.makeText(activity_register.this, "Password is Required",
//                            Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Alert")
                            .setMessage("Password is Required")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                    return;
                }

                if (password.length()<=6) {
                    Toast.makeText(RegisterActivity.this, "Password must be more than 6 Character",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Map<String, Object> firestoreUser = new HashMap<>();
                                    firestoreUser.put("name", name);
                                    firestoreUser.put("email", email);
                                    firestoreUser.put("contactNo", contactNo);

                                    db.collection("users").document(user.getUid()).set(firestoreUser)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    User user1 = new User();
                                                    user1.setUserId(user.getUid());
                                                    user1.setName(name);
                                                    user1.setContactNo(contactNo);
                                                    user1.setEmail(email);
                                                    user1.setAdvisorId("");
                                                    user1.setHotelId("");
                                                    user1.setCabId("");

                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                    intent.putExtra("user", user1);
                                                    startActivity(intent);
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    e.printStackTrace();
                                                }
                                            });
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        lblGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}