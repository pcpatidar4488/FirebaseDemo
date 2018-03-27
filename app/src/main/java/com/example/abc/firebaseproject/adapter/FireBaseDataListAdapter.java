package com.example.abc.firebaseproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc.firebaseproject.R;

import java.util.List;
import java.util.Map;

/**
 * Created by abc on 3/27/2018.
 */

public class FireBaseDataListAdapter extends RecyclerView.Adapter<FireBaseDataListAdapter.ViewHolder> {

    Context context;
    List<Map<String,String>> lisMap;
    public FireBaseDataListAdapter(Context context, List<Map<String,String>> lisMap) {
        this.context=context;
        this.lisMap=lisMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_firebase_dta_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.text1.setText(lisMap.get(position).get("username"));
        System.out.println("PPPPPPPP "+lisMap.toString());

    }

    @Override
    public int getItemCount() {
        return lisMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text1;
        TextView text2;


        public ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }
}
