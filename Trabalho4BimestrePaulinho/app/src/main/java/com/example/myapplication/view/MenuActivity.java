package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MenuActivity extends AppCompatActivity {

    private Button btCadastroPDV;
    private Button btRPagamento;
    private Button btNF;
    private View viewAlert;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void abrirCadastroPedido(View view) {
        Intent intent = new Intent(this,
                PedidoActivity.class);
        startActivity(intent);

    }
}

