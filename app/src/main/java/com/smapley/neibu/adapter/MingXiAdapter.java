package com.smapley.neibu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smapley.neibu.R;

import java.util.List;
import java.util.Map;

/**
 * Created by hao on 2015/11/9.
 */
public class MingXiAdapter extends BaseAdapter {

    private List<Map<String,String>> list;
    private LayoutInflater inflater;
    private Context context;

    public MingXiAdapter(Context context, List<Map<String, String>> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Map<String,String> map= list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_mingxi_item, null);
            viewHolder = new ViewHolder();
            viewHolder.item2 = (TextView) convertView.findViewById(R.id.list_zhangdan_item2);
            viewHolder.item3 = (TextView) convertView.findViewById(R.id.list_zhangdan_item3);
            viewHolder.item4 = (TextView) convertView.findViewById(R.id.list_zhangdan_item4);
            viewHolder.item5 = (TextView) convertView.findViewById(R.id.list_zhangdan_item5);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item2.setText(map.get("number"));
        viewHolder.item3.setText(map.get("gold"));
        viewHolder.item4.setText(map.get("pei"));
        switch (Integer.parseInt(map.get("zt"))){
            case 0:
                viewHolder.item5.setText("未打印");
                break;
            case 1:
                viewHolder.item5.setText("已打印");
                break;
            case 9:
                viewHolder.item5.setText("已退码");
                break;
        }

        return convertView;
    }

    public class ViewHolder {
        TextView item2;
        TextView item3;
        TextView item4;
        TextView item5;
    }
}