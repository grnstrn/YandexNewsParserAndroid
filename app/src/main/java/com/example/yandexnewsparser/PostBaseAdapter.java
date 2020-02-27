package com.example.yandexnewsparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PostBaseAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<PostValue> postValueArrayList;

    public PostBaseAdapter(Context context, ArrayList<PostValue> postValueArrayList) {
        this.inflater = LayoutInflater.from(context);
        this.postValueArrayList = postValueArrayList;
    }

    @Override
    public int getCount() {
        return postValueArrayList.size();
    }

    @Override
    public PostValue getItem(int position) {
        return postValueArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_row, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        PostValue postValue = getItem(position);
        viewHolder.tvTitle.setText(postValue.getTitle());
        viewHolder.tvDescr.setText(postValue.getDescr());
        viewHolder.tvDate.setText(postValue.getDate());
        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle, tvDescr, tvDate, tvLink;

        public ViewHolder(View item){
            tvTitle = (TextView ) item.findViewById(R.id.title);
            tvDescr = (TextView) item.findViewById(R.id.wrap); //rewrite
            tvDate = (TextView) item.findViewById(R.id.date);
            tvLink = (TextView) item.findViewById(R.id.link);

        }
    }
}
