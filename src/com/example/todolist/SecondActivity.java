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
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputtext);
		Button ok = (Button) findViewById(R.id.OK);
		itemname = (EditText) findViewById(R.id.editText1);
		
		Todolistmanager.initManager(this);
		
		
		ok.setOnClickListener(new getItemName());
		
		}
	
	
	class getItemName implements android.view.View.OnClickListener{
		
		@Override
		public void onClick(View v)
		{
			
			//try
			text = itemname.getText().toString();
			if (text.equals("")){
				Toast.makeText(getApplicationContext(),"Please Enter some word.",Toast.LENGTH_SHORT).show();
			}
			
			Individulitem s = new Individulitem (text);
			test.add(s);
			
		    adapter = new ArrayTypeAdapter<?>(this,
			        android.R.layout.main, test);
			    listview.setAdapter(adapter);

			    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//tc = new TodoController();
			//tc.addStudent(s);
			//Toast.makeText(getApplicationContext(),"Please Enter some 123.",Toast.LENGTH_SHORT).show();

			//TODOlist.addTodo(text);
			 //setViewValue(android.view.View, Object, String)
			//ArrayAdapter<TodolistArray>adapter = new ArrayAdapter<TodolistArray>(this ,R.layout.item,TODOlist); 
			//Intent getItemename = new Intent(SecondActivity.this , TodolistArray.class);
			// TODO Auto-generated method stub
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

