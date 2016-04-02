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
public class MainAdapter extends BaseAdapter {

    private List<Map<String, String>> list;
    private LayoutInflater inflater;
    private Context context;

    public MainAdapter(Context context, List<Map<String, String>> list) {
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
        final Map<String, String> map = list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.main_item, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.main_item_text);
            viewHolder.type = (TextView) convertView.findViewById(R.id.main_item_type);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(map.get("zhang"));
        switch (Integer.parseInt(map.get("type"))){
            case 0:
                viewHolder.type.setText("会员");
                break;
            case 1:
                viewHolder.type.setText("管理");
                break;
        }
        return convertView;
    }

    public class ViewHolder {
        TextView text;
        TextView type;
    }
}
