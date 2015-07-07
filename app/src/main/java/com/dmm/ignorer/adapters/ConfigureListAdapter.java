package com.dmm.ignorer.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmm.ignorer.R;
import com.dmm.ignorer.domain.CallInfo;

/**
 * Created by waldekd on 2015-07-06.
 */
public class ConfigureListAdapter extends ArrayAdapter<CallInfo>{
    private Context ctx;
    private int layoutResourceId;
    private CallInfo[] data = null;

    public ConfigureListAdapter(Context context, int resource, CallInfo[] objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.ctx = context;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CallInfoHolder holder;

        if(row == null){
            LayoutInflater inflater = ((Activity)ctx).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CallInfoHolder();
            holder.image = (ImageView)row.findViewById(R.id.imgIcon);
            holder.text = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }else{
            holder = (CallInfoHolder)row.getTag();
        }

        CallInfo callInfo = data[position];
        holder.image.setImageResource(callInfo.isActive() ? R.drawable.rsz_confirmation_icon : R.drawable.rsz_inactive);
        holder.text.setText(callInfo.getPhone_number());

        return row;
    }

    static class CallInfoHolder{
        ImageView image;
        TextView text;
    }
}
