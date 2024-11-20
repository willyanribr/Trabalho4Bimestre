package com.example.meuapppdv.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.meuapppdv.R;
import com.example.meuapppdv.R.id;
import com.example.meuapppdv.adapter.RelatorioListAdapter;
import com.example.meuapppdv.controller.PedidoController;
import com.example.meuapppdv.model.Pedido;

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