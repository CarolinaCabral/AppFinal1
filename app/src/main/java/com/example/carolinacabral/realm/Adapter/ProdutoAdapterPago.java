package com.example.carolinacabral.realm.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.content.Context;

import com.example.carolinacabral.realm.Domain.Produto;
import com.example.carolinacabral.realm.R;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;



/**
 * Created by Carolina Cabral on 21/11/2017.
 */

public class ProdutoAdapterPago extends RealmBaseAdapter<Produto> implements ListAdapter {
    public ProdutoAdapterPago(Context context, RealmResults<Produto>realmResults, boolean automaticUpdate)
    {
        super(context,realmResults,automaticUpdate);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustowViewHolder holder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.produtopago,parent,false);
            holder = new CustowViewHolder();
            convertView.setTag(holder);
            holder.textProdPago = (TextView) convertView.findViewById(R.id.textView12produtopago);
            holder.textTotalPago = (TextView) convertView.findViewById(R.id.textView13totalprodutopago);
        } else {
            holder = (CustowViewHolder) convertView.getTag();
        }
        final Produto p = realmResults.get(position);
        holder.textProdPago.setText(p.getDescricao());
        holder.textTotalPago.setText(String.valueOf(p.getQuantidade()*p.getPreco()));
        return convertView;
    }

    private static class CustowViewHolder{
        TextView textProdPago;
        TextView textTotalPago;
    }
}