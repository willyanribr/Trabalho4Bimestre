package com.example.myapplication.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AlunoListAdapter;
import com.example.myapplication.controller.AlunoController;
import com.example.myapplication.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
public class PedidoActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroPedido;
    private AlertDialog dialog;
    private AlunoController controller;
    private EditText edRa;
    private EditText edNome;
    private View viewAlert;
    private RecyclerView rvAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new AlunoController(this);
        rvAlunos = findViewById(R.id.rvPedidos);
        btCadastroPedido = findViewById(R.id.btCadastroPedido);
        btCadastroPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaAluno();
    }

    private void abrirCadastro() {
        //Carregando o arquivo xml do layout
        viewAlert = getLayoutInflater()
                .inflate(R.layout.dialog_cadastro_pedido, null);

        edRa = viewAlert.findViewById(R.id.edNome);
        edNome = viewAlert.findViewById(R.id.edProduto);
        edQuantidade = viewAlert.findViewById(R.id.edQuantidade);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO DE ALUNO");
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
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                    }
                });
            }
        });
        dialog.show();

    }

    public void salvarDados(){
        String retorno = controller.salvarAluno(edRa.getText().toString(),
                edNome.getText().toString());
        if(retorno != null){
            if(retorno.contains("RA")){
                edRa.setError(retorno);
                edRa.requestFocus();
            }
            if(retorno.contains("NOME")){
                edNome.setError(retorno);
                edNome.requestFocus();
            }
        }else{
            Toast.makeText(this,
                    "Aluno salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
            atualizarListaAluno();
        }
    }

    /**
     * Método cria e atualiza a lista de alunos
     */
    private void atualizarListaAluno(){
        ArrayList<Aluno> listaAlunos = controller.retornarTodosAlunos();
        AlunoListAdapter adapter = new AlunoListAdapter(listaAlunos, this);
        rvAlunos.setLayoutManager(new LinearLayoutManager(this));
        rvAlunos.setAdapter(adapter);
    }

}
