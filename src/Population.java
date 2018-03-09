import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Population {
		private Individus population[];
		private double populationFitness = -1;
		
		public Population(int populationSize) {
			this.population = new Individus[populationSize];
		}
		
		public Population(int populationSize, int chromosomeLength) {	
			this.population = new Individus[populationSize];
			
			for (int individualCount = 0; individualCount <populationSize; individualCount++) {
				Individus individual = new Individus(chromosomeLength);
				this.population[individualCount] = individual;
			}
		}
		
		public Individus [] getIndividuals() {
			return this.population;
		}
		
		public Individus getFittest(int rang) {
				Arrays.sort(this.population, new Comparator<Individus>() {
				public int compare(Individus o1, Individus o2) {
					if (o1.getFitness() > o2.getFitness()) {
						return -1;
					} else if (o1.getFitness() < o2.getFitness()) {
						return 1;
					}
					return 0;
				}
				});
				
				return this.population[rang];
			}
		
		
		public void setPopulationFitness(double fitness) {
			this.populationFitness = fitness;
		}
		
		public double getPopulationFitness() {
			return this.populationFitness;
		}
		
		public int taille() {
			return this.population.length;
		}
		
		public Individus setIndividus(int rang, Individus individual) {
			return population[rang] = individual;
		}
			
		public Individus getIndividus(int rang) {
			return population[rang];
		}
			
		public void shuffle() {
			Random rnd = new Random();
			
			for (int i = population.length - 1; i > 0; i--) {
				int index = rnd.nextInt(i + 1);
				Individus a = population[index];
				population[index] = population[i];
				population[i] = a;
			}
		}
		
}
