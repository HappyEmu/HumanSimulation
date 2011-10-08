package simulation;

import java.util.Random;
import java.util.Scanner;

public class Simulation 
{
	static int generationCount = 0;
	static int generationSize = 0;
	
	static Person[][] population = null;
	
	public static void main(String[] args) 
	{		
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();
		System.out.print("How many generations?: ");
		generationCount = scanner.nextInt();
		System.out.print("How many people per generation?: ");
		generationSize = scanner.nextInt();
		
		population = new Person[generationCount][generationSize];
		
		// Initialize
		for (int i = 0; i < generationCount; i++)
		{
			for (int j = 0; j < generationSize; j++)
			{
				population[i][j] = new Person();
			}
		}
		
		for (int i = generationCount -1 ; i >= 0; i--)
		{
			for (int j = 0; j < generationSize; j++)
			{	
				Person currentPerson = population[i][j];
				
				if (i != 0)
				{
					
					int randIdxA = rand.nextInt(generationSize);
					int randIdxB = rand.nextInt(generationSize);
					
					while(randIdxB == randIdxA)
						randIdxB = rand.nextInt(generationSize);
					
					Person parentA = population[i-1][randIdxA];
					Person parentB = population[i-1][randIdxB];
					
					parentA.children.add(currentPerson);
					parentB.children.add(currentPerson);
				}	
			}			
		}
		
		int diedOut = 0;
//		for (int i = 0; i < generationSize; i++)
//		{
//			if (generations[0][i].newestGenerationDescendants == 0)
//				diedOut++;
//		}
		System.out.print("Finished! " + diedOut);
	}
}
