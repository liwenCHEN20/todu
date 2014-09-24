package com.example.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


//http://developer.android.com/guide/topics/ui/ui-events.html 2014/09/20
public class MainActivity extends Activity
{
	private View fa1;
	//private EditText dothing;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		//setContentView(R.layout.item);
		setContentView(R.layout.activity_main);
		Button Add = (Button) findViewById(R.id.add);	
		Button Delet = (Button) findViewById(R.id.delet);
		//bodyText = (EditText) findViewById(R.id.body);
		//Button saveButton = (Button) findViewById(R.id.save);
		//oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		fa1 = (View) findViewById(R.id.C123);
		
		ListView listv = (ListView) findViewById(R.id.listView1);
		
		TodolistArray TODOlist= new TodolistArray();
		
		
		ArrayList<HashMap<String, Object>> p = new ArrayList<HashMap<String, Object>>();
		
		for (Individulitem s : TODOlist.getStudents()){
			p.add(s.toMap());
		}
		
		String[] from = new String[] {"title", "checked"};
	    int[] to = new int[] { R.id.checkBox1, R.id.textView1 };
		
		SimpleAdapter adapter = new SimpleAdapter(this, p, R.layout.item,  from, to);
		
		
		
		listv.setAdapter(adapter);
		
		
		
	//http://developer.android.com/reference/android/widget/Button.html 2014/9/21
	//https://www.youtube.com/watch?v=fxjIA4HIruU&feature=youtu.be 2014/9/21
		Add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View fa1){
				Intent intent=new Intent(MainActivity.this, SecondActivity.class); 
				startActivity(intent);
			}
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
