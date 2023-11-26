package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.Pedido;
import java.util.ArrayList;

public class RelatorioListAdapter extends RecyclerView.Adapter<RelatorioListAdapter.ViewHolder> {
    private ArrayList<Pedido> listaPedidos;
    private Context context;

    public RelatorioListAdapter(ArrayList<Pedido> listaPedidos, Context context) {
        this.listaPedidos = listaPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_relatorio_pedido,
                parent, false);

        return new RelatorioListAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Pedido pedidoSelecionado = listaPedidos.get(position);
        holder.tvCodigo.setText(String.valueOf(pedidoSelecionado.getCodigo()));
        holder.tvNomeCliente.setText(pedidoSelecionado.getNomeCliente());
        holder.tvNomeProduto.setText(pedidoSelecionado.getNomeProduto());
        holder.tvQuantidade.setText(String.valueOf(pedidoSelecionado.getQuantidade()));
        holder.tvValorUnitario.setText(String.valueOf(pedidoSelecionado.getValorUnitario()));
        holder.tvValorTotal.setText(String.valueOf(pedidoSelecionado.getValorTotal()));
    }

    @Override
    public int getItemCount() {
        return this.listaPedidos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCodigo;
        public TextView tvNomeCliente;
        public TextView tvNomeProduto;
        public TextView tvQuantidade;
        public TextView tvValorUnitario;
        public TextView tvValorTotal;
        public TextView tvFormaPagamento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvNomeCliente = itemView.findViewById(R.id.tvCliente);
            this.tvNomeProduto = itemView.findViewById(R.id.tvProduto);
            this.tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            this.tvValorUnitario = itemView.findViewById(R.id.tvValorUnitario);
            this.tvValorTotal = itemView.findViewById(R.id.tvValorTotal);
            this.tvFormaPagamento = itemView.findViewById(R.id.edFormaPagamento);
        }
    }
}
