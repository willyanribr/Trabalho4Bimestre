package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.R.id;
import com.example.myapplication.adapter.PedidoListAdaper;
import com.example.myapplication.adapter.RelatorioListAdapter;
import com.example.myapplication.controller.PedidoController;
import com.example.myapplication.model.Pedido;

import java.util.ArrayList;

public class RelatorioActivity extends AppCompatActivity {
    private PedidoController controller;
    private RecyclerView rvPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        controller = new PedidoController(this);
        rvPedidos = findViewById(R.id.rvPedidos);

        atualizarListaPedido();
    }
    private void atualizarListaPedido() {
        ArrayList<Pedido> listaPedidos = controller.retornarRelatorio();
        RelatorioListAdapter adapter = new RelatorioListAdapter(listaPedidos, this);
        rvPedidos.setLayoutManager(new LinearLayoutManager(this));
        rvPedidos.setAdapter(adapter);
    }
}