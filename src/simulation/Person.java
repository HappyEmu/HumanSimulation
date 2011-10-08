package simulation;

import java.util.ArrayList;

public class Person 
{
	public int newestGenerationDescendants;
	public ArrayList<Person> children;
	
	public Person()
	{
		this.children = new ArrayList<Person>();
		this.newestGenerationDescendants = 0;
	}
}
