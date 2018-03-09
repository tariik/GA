
 
/****************************************************************************************************
* 								   ALGORITHME GENETIQUE
******************************************************************************************************/
public class AG {
	private int taillePopulation;
	private double mutationValeur;
	private double croisementValeur;
	private int elitismCount;
	/*******************************************************************************************
	*                                      PARAMETRE
	*******************************************************************************************/

	public AG(int taillePopulation, double mutationValeur, double croisementValeur, int elitismCount) {
		this.taillePopulation = taillePopulation;
		this.mutationValeur = mutationValeur;
		this.croisementValeur = croisementValeur;
		//this.elitismCount = elitismCount;
	}
	/*******************************************************************************************
	*                             INITIALISATION DU POPULATION 
	*******************************************************************************************/

	public Population initPopulation(int chromosomeLength) {
			Population population = new Population(this.taillePopulation,chromosomeLength);
			return population;
	}
	/*******************************************************************************************
	*                                   FONCTION FITNESS
	*******************************************************************************************/

	public double calcFitness(Individus individus) {
			// Track number of correct genes
			int correctGenes = 0;
			
			// Loop over individual's genes
			for (int geneIndex = 0; geneIndex < individus.getChromosomeTaille(); geneIndex++) {
				
				// Add one fitness point for each "1" found
				if (individus.getGene(geneIndex) == 1) {
					correctGenes += 1;
				}
			}
			
			// Calculate fitness
			double fitness = (double) correctGenes / individus.getChromosomeTaille();
			
			// save fitness
			individus.setFitness(fitness);
			return fitness;

	}
	/*******************************************************************************************
	*                           EVALUATION  DE LA FONCTION FITNESS
	*******************************************************************************************/
	
	//evaluation de la fonction fitness des individus
	public void evalPopulation(Population population) {
		double populationFitness = 0;
		
		for (Individus individus : population.getIndividuals()) {
			populationFitness += calcFitness(individus);
		}
		
		population.setPopulationFitness(populationFitness);
	}
	
	/*******************************************************************************************
	*                               CONDITION D'ARRETE 
	*******************************************************************************************/
	
	public boolean isTerminationConditionMet(Population population) {
			for (Individus individus : population.getIndividuals()) {
				if (individus.getFitness() == 1) {
					return true;
				}
			}
			return false;
		}
	/*******************************************************************************************
	*						       ROULETTE SELECTION
	*******************************************************************************************/
	
	public Individus selectParent(Population population) {
		
		// Get individuals
		Individus individuals[] = population.getIndividuals();
		
		// Spin roulette wheel
		double populationFitness = population.getPopulationFitness();
		double rouletteWheelPosition = Math.random() * populationFitness;
		
		// Find parent
		double spinWheel = 0;
		
		for (Individus individus : individuals) {
			spinWheel += individus.getFitness();
			
			if (spinWheel >= rouletteWheelPosition) {
				return individus;
			}
		}
		return individuals[population.taille() - 1];
	}
	
	/*******************************************************************************************
	*                                       CROISEMENTS
	*******************************************************************************************/
	public Population croisementPopulation(Population population) {
		
		// Create new population
		Population newPopulation = new Population(population.taille());
		
		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.taille(); populationIndex++) {
			
			Individus parent1 = population.getFittest(populationIndex);
			
			// Apply Croisement to this individual?
			if (this.croisementValeur > Math.random() && populationIndex > this.elitismCount) {
				
				
				// Initialize offspring
				Individus offspring = new Individus(parent1.getChromosomeTaille());
		
				// Find second parent
				Individus parent2 = selectParent(population);
		
				// Loop over genome
				for (int geneIndex = 0; geneIndex < parent1.getChromosomeTaille(); geneIndex++) {
					// Use half of parent1's genes and half of
					if (0.5 > Math.random()) {
						offspring.setGene(geneIndex,parent1.getGene(geneIndex));
					} else {
						offspring.setGene(geneIndex,parent2.getGene(geneIndex));
					}
				}
			
				// Add offspring to new population
			newPopulation.setIndividus(populationIndex,offspring);
			} else {
				// Add individual to new population without applying Croisement 
				newPopulation.setIndividus(populationIndex, parent1);
			}
		}
		return newPopulation;
	}
	/*******************************************************************************************
	*                                       MUTATION
	*******************************************************************************************/
	
	public Population mutationPopulation(Population population) {
		// Initialize new population
		Population newPopulation = new Population(this.taillePopulation);
		
		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.taille();populationIndex++) {
			Individus individus = population.getFittest(populationIndex);
		
			// Loop over individual's genes
			for (int geneIndex = 0; geneIndex < individus.getChromosomeTaille(); geneIndex++) {
				
				// Skip mutation if this is an elite individual
				if (populationIndex >= this.elitismCount) {
					
					// Does this gene need mutation?
					if (this.mutationValeur > Math.random()) {
						// Get new gene
						int newGene = 1;
						if (individus.getGene(geneIndex) == 1) {
							newGene = 0;
						}
						
						// Mutate gene
						individus.setGene(geneIndex, newGene);
					}//if
				}//if
		}//for2
		
			// Add individual to population
		newPopulation.setIndividus(populationIndex, individus);
		}//for1
		// Return mutated population
		return newPopulation;
	}//mutation
}//class