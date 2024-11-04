package com.example.myapplication.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PedidoListAdaper;
import com.example.myapplication.controller.PedidoController;
import com.example.myapplication.model.FormaPagamento;
import com.example.myapplication.model.Pedido;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class PedidoActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroPedido;
    private AlertDialog dialog;
    private PedidoController controller;
    private EditText edNomeCliente;
    private EditText ednomeProduto;
    private EditText edQuantidade;
    private EditText edValorUnitario;
    private Spinner spinnerPagamento;
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

        edNomeCliente = viewAlert.findViewById(R.id.edNome);
        ednomeProduto = viewAlert.findViewById(R.id.edProduto);
        edQuantidade = viewAlert.findViewById(R.id.edQuantidade);
        edValorUnitario = viewAlert.findViewById(R.id.edValorUnitario);
        spinnerPagamento = viewAlert.findViewById(R.id.spinnerPagamento);
        FormaPagamento[] formasPagamento = {
                new FormaPagamento(1, "Dinheiro"),
                new FormaPagamento(2, "Cartão"),
                new FormaPagamento(3, "PIX"),
                new FormaPagamento(4, "Boleto")};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Arrays.stream(formasPagamento).map(FormaPagamento::getNome).
                toArray(String[]::new));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPagamento.setAdapter(adapter);

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
//validação de itens vazios
        String msgErro = controller.checkDadosInformados(
                edNomeCliente.getText().toString(),
                ednomeProduto.getText().toString(),
                edQuantidade.getText().toString(),
                edValorUnitario.getText().toString(),
                spinnerPagamento.getSelectedItem().toString());

        if (msgErro.equals("") || msgErro.isEmpty()) {
            controller.salvarPedido(
                    edNomeCliente.getText().toString(),
                    ednomeProduto.getText().toString(),
                    edQuantidade.getText().toString(),
                    edValorUnitario.getText().toString(),
                    spinnerPagamento.getSelectedItem().toString());

            Toast.makeText(this, "Pedido salvo com sucesso!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
            atualizarListaPedido();
        } else {
            Toast.makeText(this, msgErro, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método atualiza a lista de alunos
     */
    private void atualizarListaPedido() {
        ArrayList<Pedido> listaPedidos = controller.retornarTodosPedidos();
        PedidoListAdaper adapter = new PedidoListAdaper(listaPedidos, this);
        rvPedidos.setLayoutManager(new LinearLayoutManager(this));
        rvPedidos.setAdapter(adapter);
    }

}
