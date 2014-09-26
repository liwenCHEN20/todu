package com.example.todolist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Individulitem implements Serializable
{

	private String things;
	private boolean checked;

	private static final long serialVersionUID = -7556743916038416941L;
	//protected String studentName;
	
	public Individulitem(){
		this.things = null;
	}
	
	public Individulitem(String thing)
	{

		this.things = thing;
	}

	public String getName()
	{

		return this.things;
	}

	public String toString()
	{

		return getName();
	}
	
	public boolean checked()
	{
		return this.checked;
	}
	
	public HashMap<String, Object> toMap()
	{
		
		HashMap<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("title", this.getName());
		tmp.put("checked", this.checked());
		return tmp;
		
	}
	
/*
	public boolean equals(Object compareStudent)
	{

		if (compareStudent != null
				&& compareStudent.getClass() == this.getClass())
		{
			return this.equals((Individulitem) compareStudent);
		} else
		{
			return false;
		}
	}

	public boolean equals(Individulitem compareStudent)
	{

		if (compareStudent == null)
		{
			return false;
		}
		return getName().equals(compareStudent.getName());
	}
*/
	public int hashCode()
	{

		return ("things:" + getName()).hashCode();
	}
	
}
