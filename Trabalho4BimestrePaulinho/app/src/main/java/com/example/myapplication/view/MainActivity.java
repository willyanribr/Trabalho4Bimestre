package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.dao.PedidoDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PedidoDao.getInstancia(this);
    }

    public void abrirCadastroPedido(View view) {
        Intent intent = new Intent(MainActivity.this,
                PedidoActivity.class);
        startActivity(intent);

    }
}