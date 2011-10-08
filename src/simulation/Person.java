package simulation;

import java.util.ArrayList;

public class Person 
{
	public Person parentA;
	public Person parentB;
	public int id;
	public ArrayList<Person> children;
	
	public Person()
	{
		this.id = -1;
		this.parentA = null;
		this.parentB = null;
		this.children = new ArrayList<Person>();
	}
	public Person(Person parentA, Person parentB, int id)
	{
		this.id = id;
		this.parentA = parentA;
		this.parentB = parentB;
		this.children = new ArrayList<Person>();
		
		parentA.children.add(this);
		parentB.children.add(this);
	}
	
	public Person(int id)
	{
		this.id = id;
		this.parentA = null;
		this.parentB = null;
		this.children = new ArrayList<Person>();
	}
}
