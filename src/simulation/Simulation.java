package simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Simulation 
{
	static int numGenerations = 0;
	static int generationSize = 0;
	
	static Person[][] generations = null;
	
	public static void main(String[] args) 
	{		
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many generations?: ");
		numGenerations = scanner.nextInt();
		System.out.print("How many people per generation?: ");
		generationSize = scanner.nextInt();
		
		generations = new Person[numGenerations][generationSize];
		
		// Initialize generation 0
		for (int i = 0; i < generationSize; i++)
		{
			generations[0][i] = new Person(i);
		}
		
		for (int i = 1; i < numGenerations; i++)
		{
			for (int j = 0; j < generationSize; j++)
			{				
				Random rand = new Random();
				int randIdxA = rand.nextInt(generationSize);
				int randIdxB = rand.nextInt(generationSize);
				
				while(randIdxB == randIdxA)
					randIdxB = rand.nextInt(generationSize);
				
				Person parentA = generations[i-1][randIdxA];
				Person parentB = generations[i-1][randIdxB];
				
				int idx = i * generationSize + j;
				
				generations[i][j] = new Person(parentA, parentB, idx);
			}
		}
		System.out.println("Finished");
	}
}
