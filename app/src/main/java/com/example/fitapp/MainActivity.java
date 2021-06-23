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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private EditText editTextUsuario;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

    }
    public void buttonCadastroOnClick(View view){

        Intent intentCadastro = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intentCadastro);
    }

    public void buttonLoginOnClick(View view){
        String usuario = editTextUsuario.getText().toString();
        String senha = editTextSenha.getText().toString();
        if (usuario.equals("")||senha.equals("")){
            Toast.makeText(this, "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();


        }else{
            loginUser(usuario,senha);
        }
    }

    public void loginUser(String email, String password){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(firebaseUser != null){
                                Intent intent = new Intent(MainActivity.this, TelaPrincipalActivity.class);
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Login failed", Toast.LENGTH_LONG).show();
                            Log.e("FIREBASELOGIN", "Login Error"+task.getException().toString());
                        }
                    }
                });
    }
}