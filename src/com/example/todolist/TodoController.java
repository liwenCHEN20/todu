package com.example.todolist;

import java.io.IOException;
import java.io.PrintStream;

import android.widget.Toast;

public class TodoController
{

	// Lazy Singleton
	private static TodolistArray todoList = null;

	// Warning: throws a runTimeException
	static public TodolistArray getStudentList()
	{

		if (todoList == null)
		{
			try
			{
				todoList = Todolistmanager.getManager().loadTodoList();
				todoList.addListener(new Listenr()
				{
					@Override
					public void update()
					{

						saveStudentList();
					}
				});
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(
						"Could not deserialize StudentList from StudentListManager");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(
						"Could not deserialize StudentList from StudentListManager");
			}
		}
		return todoList;
	}

	static public void saveStudentList()
	{

		try
		{
			Todolistmanager.getManager().saveTodoList(getStudentList());
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize StudentList from StudentListManager");
		}
	}



	public void addStudent(Individulitem student)
	{

		getStudentList().addtodo(student);
		//Toast.makeText(getApplicationContext(),"Please Enter some word.",Toast.LENGTH_SHORT).show();
	}


}
