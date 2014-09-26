package com.example.todolist;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.todolist.SecondActivity.getItemName;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class ThirdActivity extends Activity
{

	private MyAdapter adapter2;
	private TodolistArray TODOlist2 = new TodolistArray();
	private ListView listv;
	private static final String FILENAME2 = "file2.sav";
	private Gson gson = new Gson();
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.archive);
		Button back = (Button) findViewById(R.id.button1);
		listv = (ListView) findViewById(R.id.archiveview);

		adapter2 = new MyAdapter(ThirdActivity.this, R.layout.item,
				TODOlist2.getStudents());
		listv.setAdapter(adapter2);

		adapter2.notifyDataSetChanged();

		back.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View fa1)
			{

				finish();
			}
		});
		listv.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			// setting onItemLongClickListener and passing the position to the
			// function
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3)
			{

				removeItemFromList(position);

				return true;
			}
		});
	}

	protected void removeItemFromList(int position)
	{

		final int deletePosition = position;

		AlertDialog.Builder alert = new AlertDialog.Builder(ThirdActivity.this);

		alert.setTitle("Delete");
		alert.setMessage("Do you want delete this item?");
		alert.setPositiveButton("YES", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{

				// TOD O Auto-generated method stub

				// main code on after clicking yes
				TODOlist2.getStudents().remove(deletePosition);
				// SecondActivity.item12.remove(deletePosition);
				saveInFile(TODOlist2, FILENAME2);
				adapter2.notifyDataSetChanged();
				adapter2.notifyDataSetInvalidated();

			}
		});
		alert.setNegativeButton("CANCEL", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{

				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		alert.show();

	}

	protected void onStart()
	{

		// TODO Auto-generated method stub
		super.onStart();
		TODOlist2 = loadFromFile(FILENAME2);
		if (TODOlist2.getStudents() == null)
			TODOlist2 = new TodolistArray();
		// adapter = new ArrayAdapter<LonelyTweetModel>(this,
		// R.layout.list_item,
		// tweets);
		// oldTweetsList.setAdapter(adapter);
		adapter2 = new MyAdapter(ThirdActivity.this, R.layout.item,
				TODOlist2.getStudents());
		listv.setAdapter(adapter2);
	}

	private TodolistArray loadFromFile(String FILENAME)
	{

		TodolistArray temp = null;
		try
		{
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			// Following line from
			// https://sites.google.com/site/gson/gson-user-guide 2014Sep23
			Type listType = new TypeToken<TodolistArray>()
			{
			}.getType();
			temp = gson.fromJson(in, listType);

		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	private void saveInFile(TodolistArray individual, String FILENAME)
	{

		try
		{
			FileOutputStream fos = openFileOutput(FILENAME, 0);
			// Gson gson = new Gson();
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(individual, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
