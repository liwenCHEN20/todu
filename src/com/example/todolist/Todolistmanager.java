package com.example.todolist;

//https://github.com/liwenCHEN20/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListManager.java 2014/9/23
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class Todolistmanager
{

	static final String prefFile = "TodoList";
	static final String slKey = "todoList";
	Context context;
	static private Todolistmanager todolistmanager = null;
	public static void initManager(Context context)
	{
		if (todolistmanager == null)
		{
			if (context == null)
			{
				throw new RuntimeException(
						"missing context for StudentListManager");
			}
			todolistmanager = new Todolistmanager(context);
		}
	}

	public static Todolistmanager getManager()
	{

		if (todolistmanager == null)
		{
			throw new RuntimeException("Did not initialize Manager");
		}
		return todolistmanager;
	}

	public Todolistmanager(Context context)
	{

		this.context = context;
	}


	public TodolistArray loadTodoList() throws ClassNotFoundException,
			IOException
	{

		SharedPreferences settings = context.getSharedPreferences(prefFile,
				Context.MODE_PRIVATE);
		
		
		
		String todoListData = settings.getString(slKey, "");
		
		
		if (todoListData.equals(""))
		{
			return new TodolistArray();
		} else
		{
			return todoListFromString(todoListData);
		}
	}

	static public TodolistArray todoListFromString(String todoListData)
			throws ClassNotFoundException, IOException
	{

		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(
				todoListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (TodolistArray) oi.readObject();
	}

	static public String todoListToString(TodolistArray sl) throws IOException
	{

		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(sl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	public void saveTodoList(TodolistArray sl) throws IOException
	{

		SharedPreferences settings = context.getSharedPreferences(prefFile,
				Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slKey, todoListToString(sl));
		editor.commit();
	}


}
