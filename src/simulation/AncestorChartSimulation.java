package simulation;

import java.io.*;
import java.util.Random;

public class AncestorChartSimulation 
{
	static final int generationCount = 66;
	static final int generationSize = 100000;
	
	static Person[][] population = null;
	
	public static void main(String[] args) throws IOException 
	{
		population = new Person[generationCount][generationSize];
		
		System.out.println("Initializing population...");
		
		// Initialize population
		for (int i = 0; i < generationCount; i++)
		{
			for (int j = 0; j < generationSize; j++)
			{
				population[i][j] = new Person();
			}
		}
		
		System.out.println("Setting up relationships...");
		setUpRelationships(population);

		System.out.println("Evaluating results...");
		int[] results = new int[generationSize];
		evaluateResults(population, results);
		
		System.out.println("Writing results to file...");
		writeResultsToFile(results, 70, false);
		
		System.out.println("Done...");
	}
	
	static void setUpRelationships(Person[][] population)
	{
		Random rand = new Random();

		// Set up relationships
		for (int i = generationCount - 1; i > 0; i--)
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
	}

	static void evaluateResults(Person[][] population, int[] results) 
	{
		for (int i = 0; i < generationSize; i++)
		{
			int currentDescendants = population[0][i].descendants;
			if (!(currentDescendants > generationSize))
				results[currentDescendants]++;
		}
	}

	static void writeResultsToFile(int[] results, int maxValue, boolean append) throws IOException
	{
		FileWriter stream = new FileWriter("results.txt", append);
		BufferedWriter file = new BufferedWriter(stream);
		
		file.write("New simulation\n");
		for (int i = 0; i < maxValue; i++)
		{
			file.write(i + "\tdescendants:\t" + results[i] + "\tpeople\n");
		}
		file.write("\n");
		file.close();
		stream.close();
	}
}
