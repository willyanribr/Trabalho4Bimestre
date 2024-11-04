package com.example.myapplication.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.LoginController;
import com.example.myapplication.dao.LoginDao;
import com.example.myapplication.dao.PedidoDao;
import com.example.myapplication.model.Login;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsuario;
    private EditText edSenha;
    private EditText edNome;
    private AlertDialog dialog;
    private View viewAlert;
    private Button btLogin;
    private Button btCadastro;
    private LoginController loginController;
    private Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLogin = findViewById(R.id.btLogin);

        loginController = new LoginController(this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLogin();
            }
        });
        btCadastro = findViewById(R.id.btCadastro);
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastroUsuario();
            }
        });
    }

    public void abrirLogin() {

        viewAlert = getLayoutInflater().inflate(R.layout.dialog_login, null);
        edUsuario = viewAlert.findViewById(R.id.edUsuario);
        edSenha = viewAlert.findViewById(R.id.edSenha);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("TELA DE LOGIN");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Login", null);
        dialog = builder.create();
        dialog.setOnShowListener(dialogInteraface -> {

            Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Login login = new Login();
                    login = loginController.validarLogin(edUsuario.getText().toString(), edSenha.getText().toString());

                    if (login != null) {
                        if (edUsuario.getText().toString().equalsIgnoreCase(login.getUsuario()) && edSenha.getText().toString().equalsIgnoreCase(login.getSenha().toString())) {
                            abrirCadastroPedidos();

                        }else{
                            Toast.makeText(context, "Credenciais incorretas, verifique!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context, "Login inválido", Toast.LENGTH_LONG).show();
                    }
                }
            });
        });
        dialog.show();
    }

    public void abrirCadastroPedidos() {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void abrirCadastroUsuario() {
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro, null);

        edUsuario = viewAlert.findViewById(R.id.edUsuario);
        edSenha = viewAlert.findViewById(R.id.edSenha);
        edNome = viewAlert.findViewById(R.id.edNome);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("TELA DE CADASTRO");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Cadastrar", null);
        dialog = builder.create();
        dialog.setOnShowListener(dialogInteraface -> {

            Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    salvarLogin();
                }
            });
        });
        dialog.show();
    }

    public void salvarLogin() {
        String retorno = loginController.salvarLogin(edNome.getText().toString(), edUsuario.getText().toString(), edSenha.getText().toString());

        if (retorno != null) {
            if (retorno.contains("USUARIO")) {
                edUsuario.setError(retorno);
                edUsuario.requestFocus();
            }
            if (retorno.contains("SENHA")) {
                edSenha.setError(retorno);
                edSenha.requestFocus();
            }
        } else {
            Toast.makeText(this, "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();

            dialog.dismiss();
        }
    }
}
