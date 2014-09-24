package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

public class TodolistArray
{

	protected ArrayList<Individulitem> TodoList = null;	
	protected ArrayList<Listenr> listeners = null;
	//private String reseave;

	public TodolistArray()
	{

		TodoList = new ArrayList<Individulitem>();
		listeners = new ArrayList<Listenr>();
	}

	private ArrayList<Listenr> getListeners()
	{

		if (listeners == null)
		{
			listeners = new ArrayList<Listenr>();
		}
		return listeners;
	}

	public Collection<Individulitem> getStudents()
	{

		return TodoList;
	}

	public void addtodo(Individulitem testStudent)
	{

		TodoList.add(testStudent);
		notifyListeners();
	}

	private void notifyListeners()
	{

		for (Listenr listener : getListeners())
		{
			listener.update();
		}
	}

	public void removeStudent(Individulitem testStudent)
	{

		TodoList.remove(testStudent);
		notifyListeners();
	}

	public Individulitem chooseStudent() throws EmptyStudentListException
	{

		int size = TodoList.size();
		if (size <= 0)
		{
			throw new EmptyStudentListException();
		}
		int index = (int) (TodoList.size() * Math.random());
		return TodoList.get(index);
	}

	public int size()
	{

		return TodoList.size();
	}

	public boolean contains(Individulitem testStudent)
	{

		return TodoList.contains(testStudent);
	}

	public void addListener(Listenr l)
	{

		getListeners().add(l);
	}

	public void removeListener(Listenr l)
	{

		getListeners().remove(l);
	}
	
}
