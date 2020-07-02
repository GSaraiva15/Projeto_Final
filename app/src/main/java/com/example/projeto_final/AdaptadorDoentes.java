package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorDoentes extends RecyclerView.Adapter<AdaptadorDoentes.ViewHolderDoentes> {
    private final Context context;
    private Cursor cursor = null;

    public AdaptadorDoentes(Context context) {
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        if (cursor != this.cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolderDoentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDoente = LayoutInflater.from(context).inflate(R.layout.item_doente, parent, false);
        return new ViewHolderDoentes(itemDoente);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDoentes holder, int position) {
        cursor.moveToPosition(position);
        Doentes doentes = Converte.cursorToDoente(cursor);
        holder.setDoentes(doentes);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public Doentes getPacienteSelecionado() {
        if (viewHolderPacienteSelecionado == null) return null;
        return viewHolderPacienteSelecionado.doentes;
    }

    private AdaptadorDoentes.ViewHolderDoentes viewHolderPacienteSelecionado = null;

    public class ViewHolderDoentes extends RecyclerView.ViewHolder{
        private Doentes doentes = null;


        public ViewHolderDoentes(@NonNull View itemView) {
            super(itemView);

        }

        public void setDoentes(Doentes doentes) {
            this.doentes = doentes;
        }
    }
}