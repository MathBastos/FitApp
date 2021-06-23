package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    EditText editTextData;
    EditText editTextPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextData = findViewById(R.id.editTextData);
        editTextPeso = findViewById(R.id.editTextPeso);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void buttonRegistrarOnClick(View view){
        String data = editTextData.getText().toString();
        String peso = editTextPeso.getText().toString();

        Registros registro = new Registros(peso,data);
        DatabaseReference rootReference = firebaseDatabase.getReference();
        rootReference.child(firebaseUser.getUid()).push().setValue(registro);

        Intent intent = new Intent(RegistroActivity.this, TelaPrincipalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}