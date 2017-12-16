package com.sakib.servertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakib on 22-Mar-16.
 */
public class DetailsAdapter extends ArrayAdapter {

    List list=new ArrayList();
    DetailsHolder detailsHolder;

    public DetailsAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(details object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            detailsHolder=new DetailsHolder();
            detailsHolder.tvName= (TextView) row.findViewById(R.id.tvProduct);
            detailsHolder.tvPrice= (TextView) row.findViewById(R.id.tvPrice);
            row.setTag(detailsHolder);
        }else{
            detailsHolder=(DetailsHolder)row.getTag();


        }
        details dtl= (details) this.getItem(position);
        detailsHolder.tvName.setText(dtl.getName());
        detailsHolder.tvPrice.setText(dtl.getPrice());

        return row;
    }



    static  class DetailsHolder{
        TextView tvName,tvPrice;
    }
}
