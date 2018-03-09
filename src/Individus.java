
public class Individus {

	private int[] chromosome;
	private double fitness = -1;
	
	
	
	public Individus (int chromosomeTaille) {
		this.chromosome = new int[chromosomeTaille];
		
		for (int gene = 0; gene < chromosomeTaille; gene++) {
		
			if (0.5 < Math.random()) {
				this.setGene(gene, 1);
			} 
			
			else {
				this.setGene(gene, 0);
			}
		}
	}
	
	public int getChromosomeTaille() {
			return this.chromosome.length;
	}
	
	public void setGene(int rang, int gene) {
		this.chromosome[rang] = gene;
	}
		
	public int getGene(int rang) {
		return this.chromosome[rang];
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
		
	public double getFitness() {
		return this.fitness;
	}
		
	public String toString() {
		String output = "";
		
		for (int gene = 0; gene < this.chromosome.length; gene++) {
			output += this.chromosome[gene];
		}
		
		return output;
	}

}
