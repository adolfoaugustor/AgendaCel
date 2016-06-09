package com.example.swr2d2.agendacel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.swr2d2.agendacel.dominio.Entidades.Contato;

/**
 * Created by swr2d2 on 09/06/2016.
 */

public class ContatoArrayAdapter extends ArrayAdapter<Contato>{

    private int resource = 0;
    private LayoutInflater inflater;
    private Context context;

    public ContatoArrayAdapter(Context context, int resource)
    {
        super(context, resource);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View view = null;
        ViewHolder viewHolder = null;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();

            view = inflater.inflate(resource, parent, false);

            viewHolder.txtCor = (TextView)view.findViewById(R.id.txtCor);
            viewHolder.txtNome = (TextView)view.findViewById(R.id.txtNome);
            viewHolder.txtTelefone = (TextView)view.findViewById(R.id.txtTelefone);

            view.setTag(viewHolder);

            convertView = view;

        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
            view = convertView;
        }

        Contato contato = getItem(position);

//        if (contato.getNome().toUpperCase().startsWith("A") )
//            viewHolder.txtCor.setBackgroundColor( context.getResources().getColor(R.color .azul) );
//        else
//        if (contato.getNome().toUpperCase().startsWith("B"))
//            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.vermelho));
//        else
//            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.color1));

        viewHolder.txtNome.setText(contato.getNome());
        viewHolder.txtTelefone.setText(contato.getTelefone());

        return view;

    }

    static class ViewHolder
    {
        TextView txtCor;
        TextView txtNome;
        TextView txtTelefone;
    }


}
