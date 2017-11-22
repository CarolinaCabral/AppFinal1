package com.example.carolinacabral.realm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carolinacabral.realm.AddUpdateProduto;
import com.example.carolinacabral.realm.Domain.Produto;
import com.example.carolinacabral.realm.MainActivity;
import com.example.carolinacabral.realm.R;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by Carolina Cabral on 13/11/2017.
 */

public class ProdutoAdapter extends RealmBaseAdapter<Produto> implements ListAdapter {

    public ProdutoAdapter(Context context, RealmResults<Produto> realmResults, boolean automaticUpdate){
        super(context, realmResults, automaticUpdate);
    }

    Produto _p = new Produto();
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustowViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.produtoitem,parent,false);
            holder = new CustowViewHolder();
            convertView.setTag(holder);
            holder.textCliente = (TextView) convertView.findViewById(R.id.textView3Cliente);
            holder.textDesc = (TextView) convertView.findViewById(R.id.textView7);
            holder.textQuant = (TextView) convertView.findViewById(R.id.textView8);
            holder.textPreco = (TextView) convertView.findViewById(R.id.textView4preco);
            holder.textPag = (TextView) convertView.findViewById(R.id.textView9);
            holder.textTotal = (TextView) convertView.findViewById(R.id.textView5total);
            holder.atualizar = (Button) convertView.findViewById(R.id.btnAtualizar);
            holder.remover = (Button) convertView.findViewById(R.id.btnRemover);

        } else {
            holder=(CustowViewHolder)convertView.getTag();
        }
        final Produto p = realmResults.get(position);
        holder.textCliente.setText(p.getCliente());
        holder.textDesc.setText(p.getDescricao());
        holder.textQuant.setText(String.valueOf(p.getQuantidade()));
        holder.textPreco.setText(String.valueOf(p.getPreco()));
        holder.textPag.setText(p.getPagamento());
        holder.textTotal.setText(String.valueOf(p.getQuantidade()*p.getPreco()));


        holder.atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(context, AddUpdateProduto.class);
                it.putExtra(Produto.ID,p.getId());
                context.startActivity(it);
            }
        });
        holder.remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getInstance(context);
                realm.beginTransaction();
                p.removeFromRealm();
                realm.commitTransaction();
                Toast toast = Toast.makeText(context,"Produto excluído",Toast.LENGTH_SHORT);
                toast.show();
                realm.close();

            }
        });


        return convertView;
    }
    private  static class CustowViewHolder{
        TextView textCliente;
        TextView textDesc;
        TextView textQuant;
        TextView textPreco;
        TextView textPag;
        TextView textTotal;
        Button atualizar;
        Button remover;
    }
}
