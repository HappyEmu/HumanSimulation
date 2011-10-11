package simulation;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Simulation 
{
	static final int generationCount = 66;
	static final int generationSize = 100000;
	
	static Person[][] population = null;
	
	public static void main(String[] args) throws IOException 
	{
		FileWriter stream = new FileWriter("results.txt", true);
		BufferedWriter file = new BufferedWriter(stream);
		Random rand = new Random();
		System.out.println("Simulation running...");
		
		population = new Person[generationCount][generationSize];
		
		System.out.println("Generating population...");
		
		// Initialize population
		for (int i = 0; i < generationCount; i++)
		{
			for (int j = 0; j < generationSize; j++)
			{
				population[i][j] = new Person();
			}
		}
		System.out.println("Setting up relationships...");
		// Set up relationships
		for (int i = generationCount -1 ; i > 0; i--)
		{
			for (int j = 0; j < generationSize; j++)
			{	
				Person currentPerson = population[i][j];
					
				int randIdxA = rand.nextInt(generationSize);
				int randIdxB = rand.nextInt(generationSize);
				
				while(randIdxB == randIdxA)
					randIdxB = rand.nextInt(generationSize);
				
				Person parentA = population[i-1][randIdxA];
				Person parentB = population[i-1][randIdxB];
				
				parentA.descendants = (1 + currentPerson.descendants);
				parentB.descendants = (1 + currentPerson.descendants);

			}			
		}
		System.out.println("Evaluating...");
		int[] results = new int[generationSize];
		for (int i = 0; i < generationSize; i++)
		{
			int currentDescendants = population[0][i].descendants;
			if (!(currentDescendants > generationSize))
				results[currentDescendants]++;
		}
		int totalDescendants = 0;
		for (int desc : results)
		{
			totalDescendants += desc;
		}
		System.out.print("Total descendants in generation 1: " + totalDescendants);
		file.write("New simulation\n");
		for (int i = 0; i < 100; i++)
		{
			file.write(i + "\tdescendants:\t" + results[i] + "\tpeople\n");
		}
		file.write("\n");
		file.close();
		stream.close();
	}
}
