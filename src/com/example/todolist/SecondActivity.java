package com.example.todolist;

import java.util.ArrayList;

import com.google.gson.internal.bind.ArrayTypeAdapter;

import android.R.string;
import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class SecondActivity extends Activity
{
	private View  getin;
	private EditText itemname;
	private String text;
	private ArrayList<TodolistArray> itemlist;
	private TodoController tc;
	private ArrayList<Individulitem> test;
	private ArrayAdapter<Individulitem> adapter;
	private ListView listview;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputtext);
		Button ok = (Button) findViewById(R.id.OK);
		itemname = (EditText) findViewById(R.id.editText1);
		
		Todolistmanager.initManager(this);
		
		
		ok.setOnClickListener(new getItemName());
		
		}
	
	
	public class getItemName implements android.view.View.OnClickListener{
		
		@Override
		public void onClick(View v)
		{

			// try
			text = itemname.getText().toString();
			if (text.equals(""))
			{
				Toast.makeText(getApplicationContext(),
						"Please Enter some word.", Toast.LENGTH_SHORT).show();
			}
			Individulitem s = new Individulitem(text);
			Intent resultIntent = new Intent();
			//item12.add(text);
			resultIntent.putExtra("data", text);
			setResult(Activity.RESULT_OK, resultIntent);
			finish();

		}
		

		public boolean onCreateOptionsMenu(Menu menu)
		{

			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;

		}


	}
}

