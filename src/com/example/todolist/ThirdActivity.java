package com.example.todolist;

import com.example.todolist.SecondActivity.getItemName;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ThirdActivity extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.archive);
	Button back = (Button) findViewById(R.id.button1);

//	Todolistmanager.initManager(this);

	back.setOnClickListener(new View.OnClickListener()
	{

		public void onClick(View fa1)
		{
			finish();
		}
	});
	}

}
