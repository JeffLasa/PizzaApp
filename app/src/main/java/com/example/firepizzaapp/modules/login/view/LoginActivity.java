package com.example.firepizzaapp.modules.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.firepizzaapp.CadastrarActivity;
import com.example.firepizzaapp.MainActivity;
import com.example.firepizzaapp.R;
import com.example.firepizzaapp.modules.login.viewmodel.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;
    private Button cadastrarButton;
    private LoginViewModel loginViewModel;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        emailEditText = findViewById(R.id.email_id);
        senhaEditText = findViewById(R.id.senha_id);
        loginButton = findViewById(R.id.button_enviar_id);
        cadastrarButton = findViewById(R.id.button_cadastrar_id);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(v -> logar());


        cadastrarButton.setOnClickListener(v -> irParaCadastrar());


        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getAutenticadorLiveData()
                .observe(this, autenticado ->{
                    if(autenticado){
                        irParaMain();
                    }else{
                        Toast.makeText(this,"Falha na autenticação",Toast.LENGTH_LONG).show();
                    }
                });

        loginViewModel.getLoaderLiveData()
                .observe(this,showLoader ->{
                    progressBar.setVisibility(showLoader ? View.VISIBLE: View.GONE);
                });

    }

    private void irParaCadastrar() {
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
    }


    private void logar() {

            String email = emailEditText.getEditableText().toString();
            String senha = senhaEditText.getEditableText().toString();

            loginViewModel.autenticarUsuario(email, senha);

    }

    private void irParaMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
