package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorDoentes extends RecyclerView.Adapter<AdaptadorDoentes.ViewHolderDoentes>{
    private final Context context;
    private Cursor cursor= null;

    public AdaptadorDoentes(Context context){this.context = context;}

    public void setCursor(Cursor cursor){
        if(cursor != this.cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public ViewHolderDoentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDoente = LayoutInflater.from(context).inflate(R.layout.item_doente,parent, false);
        return new ViewHolderDoentes(itemDoente);
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorDoentes.ViewHolderDoentes holder, int position) {
        cursor.moveToPosition(position);
        Doentes doentesMoldel = Converte.cursorToDoente(cursor);
        holder.setDoentes(doentesMoldel);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if(cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }
    public Doentes getDoenteSelecionado(){
    if(viewHolderDoenteSelecionado == null)return null;
    return viewHolderDoenteSelecionado.;
    }

    private AdaptadorDoentes.ViewHolderDoentes viewHolderDoenteSelecionado = null;

    public class ViewHolderDoentes extends RecyclerView.ViewHolder{

       public ViewHolderDoentes(@NonNull View itemView){
           super(itemView);
       }


        public void setDoentes(Doentes doentes) {
           this.doentes = doentes;
        }
    }
}