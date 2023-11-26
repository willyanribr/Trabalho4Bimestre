package com.example.myapplication.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PedidoListAdaper;
import com.example.myapplication.controller.PedidoController;
import com.example.myapplication.model.Pedido;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PedidoActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroPedido;
    private AlertDialog dialog;
    private PedidoController controller;
    private EditText edCodigo;
    private EditText edNomeCliente;
    private EditText ednomeProduto;
    private EditText edQuantidade;
    private View viewAlert;
    private RecyclerView rvPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        controller = new PedidoController(this);
        rvPedidos = findViewById(R.id.rvPedidos);
        btCadastroPedido = findViewById(R.id.btCadastroPedido);
        btCadastroPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaPedido();
    }

    private void abrirCadastro() {
        //Carregando o arquivo xml do layout
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro_pedido, null);

        edCodigo = viewAlert.findViewById(R.id.edCodigo);
        edNomeCliente = viewAlert.findViewById(R.id.edNome);
        ednomeProduto = viewAlert.findViewById(R.id.edProduto);
        edQuantidade = viewAlert.findViewById(R.id.edQuantidade);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("CADASTRO DE PEDIDOS");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Salvar", null);
        dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> {

            Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    salvarDados();
                }
            });
        });
        dialog.show();
    }

    public void salvarDados() {
        String retorno = controller.salvarPedido(
                edCodigo.getText().toString(),
                edNomeCliente.getText().toString(),
                ednomeProduto.getText().toString(),
                edQuantidade.getText().toString());

        if (retorno != null) {
            if (retorno.contains("CODIGO")) {
                edCodigo.setError(retorno);
                edCodigo.requestFocus();
            }
            if (retorno.contains("NOME CLIENTE")) {
                edNomeCliente.setError(retorno);
                edNomeCliente.requestFocus();
            }
            if (retorno.contains("NOME PRODUTO")) {
                ednomeProduto.setError(retorno);
                ednomeProduto.requestFocus();
            }
            if (retorno.contains("QUANTIDADE")) {
                edQuantidade.setError(retorno);
                edQuantidade.requestFocus();
            }

        } else {
            Toast.makeText(this,
                    "Pedido salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
            atualizarListaPedido();
        }
    }

    /**
     * Método cria e atualiza a lista de alunos
     */
    private void atualizarListaPedido() {
        ArrayList<Pedido> listaPedidos = controller.retornarTodosPedidos();
        PedidoListAdaper adapter = new PedidoListAdaper(listaPedidos, this);
        rvPedidos.setLayoutManager(new LinearLayoutManager(this));
        rvPedidos.setAdapter(adapter);
    }

}
