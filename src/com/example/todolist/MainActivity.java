package com.example.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


//http://developer.android.com/guide/topics/ui/ui-events.html 2014/09/20
public class MainActivity extends Activity
{

	private View fa1;
	private TodolistArray TODOlist1 = new TodolistArray();
	private TodolistArray TODOlist2 = new TodolistArray();
	private String resultData;
	private ListView listv;
	private MyAdapter adapter;
	private MyAdapter adapter2;
	private static ArrayList<String> item12;
	private static final String FILENAME = "file.sav";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		// setContentView(R.layout.item);
		setContentView(R.layout.activity_main);
		Button Add = (Button) findViewById(R.id.add);
		Button Delet = (Button) findViewById(R.id.delet);
		// bodyText = (EditText) findViewById(R.id.body);
		// Button saveButton = (Button) findViewById(R.id.save);
		// oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		Todolistmanager.initManager(this.getApplicationContext());
		fa1 = (View) findViewById(R.id.C123);
		listv = (ListView) findViewById(R.id.listView1);
		adapter = new MyAdapter(MainActivity.this, R.layout.item,
				TODOlist1.getStudents());
		adapter2 = new MyAdapter(MainActivity.this, R.layout.item,
				TODOlist2.getStudents());
		listv.setAdapter(adapter);

		// http://developer.android.com/reference/android/widget/Button.html
		// 2014/9/21
		// https://www.youtube.com/watch?v=fxjIA4HIruU&feature=youtu.be
		// 2014/9/21
		Add.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View fa1)
			{

				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				//Intent inarchi= new Intent(MainActivity.this, ThirdActivity.class);
				startActivityForResult(intent, 101);
				// startActivityForResult2(intent, 102);
			}
		});
		listv.setOnItemLongClickListener(new OnItemLongClickListener() {
            // setting onItemLongClickListener and passing the position to the function
                      @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int position, long arg3) {
                removeItemFromList(position);  
               
                return true;
            }
        });
		listv.setOnItemClickListener(new OnItemClickListener() {
            // setting onItemLongClickListener and passing the position to the function
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3)
					{
						
						// TODO Auto-generated method stub
						moveItemToList(arg2);
						//Intent inarchi= new Intent(MainActivity.this, ThirdActivity.class);
						adapter.notifyDataSetChanged();
						adapter2.notifyDataSetChanged();
						//startActivity(inarchi);
					}
        });
		
	
	Delet.setOnClickListener(new View.OnClickListener()
	{
		public void onClick(View fa1)
		{
			//Intent inarchive= new Intent(MainActivity.this,ThirdActivity.class);
			mail();
			
		}
	});
	}
	  protected void removeItemFromList(int position) {
	        final int deletePosition = position;
	       
	        AlertDialog.Builder alert = new AlertDialog.Builder(
	                MainActivity.this);
	   
	        alert.setTitle("Delete");
	        alert.setMessage("Do you want delete this item?");
	        alert.setPositiveButton("YES", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TOD O Auto-generated method stub
	                   
	                    // main code on after clicking yes
	            		TODOlist1.getStudents().remove(deletePosition);
	            		//SecondActivity.item12.remove(deletePosition);

	                    adapter.notifyDataSetChanged();
	                    adapter.notifyDataSetInvalidated();
	     
	            }
	        });
	        alert.setNegativeButton("CANCEL", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                dialog.dismiss();
	            }
	        });
	     
	        alert.show();
	     
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == 101)
		{
			resultData = data.getStringExtra("data");
			// System.out.println("result data is:"+resultData);
			Individulitem item = new Individulitem(resultData);
		//	item12.add(resultData);
			TODOlist1.getStudents().add(item);
			adapter.notifyDataSetChanged();
		}

	}
    public void mail(){
    	//http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application 2014/9/25
    	Intent i = new Intent(Intent.ACTION_SEND);
    	i.setType("message/rfc822");
    //	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
    	i.putExtra(Intent.EXTRA_SUBJECT, "Todolist");
    	i.putExtra(Intent.EXTRA_TEXT   , item12);
    	try {
    	    startActivity(Intent.createChooser(i, "Send mail..."));
    	} catch (android.content.ActivityNotFoundException ex) {
    	    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
    	}
    }
    
    
    
    
	  protected void moveItemToList(int position2) {
	        final int deletePosition = position2;
	       
	        AlertDialog.Builder alert = new AlertDialog.Builder(
	                MainActivity.this);
	   
	        alert.setTitle("Archive");
	        alert.setMessage("Do you want Archive this item?");
	        alert.setPositiveButton("YES", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TOD O Auto-generated method stub

	                    // main code on after clicking yes
					Intent inarchi= new Intent(MainActivity.this, ThirdActivity.class);
	            		TODOlist2.getStudents().add(TODOlist1.getStudents().get(deletePosition));
	            		TODOlist1.getStudents().remove(deletePosition);
	            		//SecondActivity.item12.remove(deletePosition);
	            		//Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
	                    adapter.notifyDataSetChanged();		
	                    adapter2.notifyDataSetChanged();
	                    adapter.notifyDataSetInvalidated();
						startActivity(inarchi);
	            }
	        });
	        alert.setNegativeButton("CANCEL", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                dialog.dismiss();
	            }
	        });
	     
	        alert.show();
	     
	    }
    
		private void loadFromFile() {
			try {
				FileInputStream fis = openFileInput(FILENAME);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));
				Gson gson = new Gson();
				// Following line from
				// https://sites.google.com/site/gson/gson-user-guide 2014Sep23
				Type listType = new TypeToken<ArrayList<Individulitem>>() {
				}.getType();
				gson.fromJson(in, listType);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		private void saveInFile() {
			try {
				FileOutputStream fos = openFileOutput(FILENAME, 0);
				Gson gson = new Gson();
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				gson.toJson(TODOlist1.getStudents(), osw);
				osw.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
