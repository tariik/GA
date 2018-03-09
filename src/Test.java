
public class Test {

	public static void main(String[] args) {
		
		// Create GA object
		AG ga = new AG(100, 0.01, 0.95, 0);
		
		//initialisation
		Population population = ga.initPopulation(34);
		
		//generation
		int generation = 1;
		

		//evolution
		while (ga.isTerminationConditionMet(population) == false) {
			// Print fittest individual from population
			
			System.out.println("Best solution: " + population.getFittest(0).toString());
			
			// Croisement
			population = ga.croisementPopulation(population);
			
			// Mutation
			population = ga.mutationPopulation(population);
			
			// Evaluation population
			ga.evalPopulation(population);
			
			// Increment generation
			generation++;
		}
		
		//Affichage des resultats
		System.out.println("Solution trouve dans la  " + generation + "eme generations");
		System.out.println("La mieulleur solution est : " + population.getFittest(0).toString());
			
	}
}