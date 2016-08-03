package com.pubnubchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pubnubchat.R;

import java.util.List;

/**
 * Created by Hari on 8/1/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.AdapterHolder> {
    public class AdapterHolder extends RecyclerView.ViewHolder {
        private TextView tv_text;

        public AdapterHolder(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

    private List<String> list;
    private Context context;

    public ChatAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void notifyList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.row_message, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        holder.tv_text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
