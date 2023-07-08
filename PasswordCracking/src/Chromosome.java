
public class Chromosome {
	private int genCount;
	private char[] genes;
	private int fitnessScore;
	
	public Chromosome(int genCount) {
		this.genCount = genCount;
		genes = new char[genCount];
		fitnessScore = 0;
	}

	public int getGenCount() {
		return genCount;
	}

	public void setGenCount(int genCount) {
		this.genCount = genCount;
	}

	public char[] getGenes() {
		return genes;
	}

	public void setGenes(char[] genes) {
		this.genes = genes;
	}

	public int getFitnessScore() {
		return fitnessScore;
	}

	public void setFitnessScore(int fitnessScore) {
		this.fitnessScore = fitnessScore;
	}
	
	public void calculateFitnessScore(String password) {  // fitness function
		for(int i = 0; i < password.length(); i++) {
			if(genes[i] != password.charAt(i))
				fitnessScore++;
		}
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < genCount; i++) {
			str += genes[i];
		}
		return str;
	}
	
	public Chromosome copy() {
		Chromosome newChr = new Chromosome(this.genCount);
		newChr.fitnessScore = this.fitnessScore;
		for(int i = 0; i < genCount; i++) {
			newChr.genes[i] = this.genes[i];
		}
		return newChr;
	}
	
}
