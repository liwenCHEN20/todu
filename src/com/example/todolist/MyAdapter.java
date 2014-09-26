package com.example.todolist;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Individulitem> {

	private Context context;
	private ArrayList<Individulitem> itemsArray;
	public MyAdapter(Context context, int resource,
			ArrayList<Individulitem> objects) {
		super(context, resource, objects);
		this.context=context;
		this.itemsArray = objects;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.item, null, false);
			CheckBox box = (CheckBox) convertView.findViewById(R.id.checkBox1);
			TextView textview = (TextView) convertView.findViewById(R.id.textView1);
			textview.setText(itemsArray.get(position).getName());
			box.setChecked(itemsArray.get(position).checked());
		}
		else
		{
			CheckBox box = (CheckBox) convertView.findViewById(R.id.checkBox1);
			TextView textview = (TextView) convertView.findViewById(R.id.textView1);
			textview.setText(itemsArray.get(position).getName());
			box.setChecked(itemsArray.get(position).checked());
		}
		
		return convertView;
	}


}
