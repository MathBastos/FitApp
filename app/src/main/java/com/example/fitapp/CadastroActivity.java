package com.example.fitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    private EditText editTextUsuarioCadastro;
    private EditText editTextSenhaCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        editTextUsuarioCadastro = findViewById(R.id.editTextUsuarioCadastro);
        editTextSenhaCadastro = findViewById(R.id.editTextSenhaCadastro);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void buttonSalvarOnClick(View view){
        String usuario = editTextUsuarioCadastro.getText().toString();
        String senha= editTextSenhaCadastro.getText().toString();

        if(usuario.equals("")||senha.equals("")) {
            Toast.makeText(this, "Por favor preencher todos os campos", Toast.LENGTH_SHORT).show();
        }else{
            createUser(usuario,senha);
        }
    }

    public void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this, "User created", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Toast.makeText(CadastroActivity.this, "User couldnt be created", Toast.LENGTH_LONG).show();
                            Log.e("FIREBASEAUTH", "Create Error" + task.getException().toString());
                        }
                    }
                });
    }
}