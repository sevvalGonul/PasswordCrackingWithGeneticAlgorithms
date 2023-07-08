import java.util.Random;

public class Population {
	private int chromosomeCount;  // Populasyondaki kromozom sayısı
	private int genCount; // Populasyondaki herbir kromozomun gen sayısı
	private int generationNum;
	private Chromosome[] chromosomes;
	private char[] characters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'!', '*', '+', '-', '.', '?', '_', ' '};
	private String password;
	private Random random = new Random();
	private final double MUTPROB = 0.05;
	
	public Population(int chromosomeCount, int genCount, String p) {
		this.chromosomeCount = chromosomeCount;
		this.generationNum = 1;
		this.chromosomes = new Chromosome[chromosomeCount];
		this.password = p;
		this.genCount = genCount;
	}

	public int getChromosomeCount() {
		return chromosomeCount;
	}

	public void setChromosomeCount(int chromosomeCount) {
		this.chromosomeCount = chromosomeCount;
	}

	public int getGeneretionNum() {
		return generationNum;
	}

	public void setGeneretionNum(int generetionNum) {
		this.generationNum = generetionNum;
	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
	}
	
	public void randomFirstGeneration() {
		for(int i = 0; i < chromosomeCount; i++) {
			Chromosome chr = new Chromosome(genCount);
			char[] genes = chr.getGenes();
			for(int j = 0; j < genCount; j++) {
				char gen = characters[random.nextInt(characters.length)];
				genes[j] = gen;
			}
			chr.calculateFitnessScore(password);
			chromosomes[i] = chr;
		}
	}
	
	public Chromosome elitism() {  // Returns a copy of best chromosome(solution) of the population
		int minFn = chromosomes[0].getFitnessScore();
		Chromosome elit = chromosomes[0];
		for(Chromosome chr : chromosomes) {
			int fn = chr.getFitnessScore();
			if(fn < minFn) {
				minFn = fn;
				elit = chr;				
			}
		}
		
		return elit.copy();
	}
	
	/* Uygunluk değeri soruda istenildiği gibi verilen kromozomun şifreden kaç karakter farklı olduğunu belirtir.
	 * Şifreye yakın olan kromozomların uygunluk değeri daha düşük olur çünkü daha az karakterleri farklıdır.
	 * Dolayısıyla Roulette Wheel algoritması ile selection yaparken uygunluk değeri daha düşük olan yanii şifreye
	 * daha yakın olan kromozomların seçilme olasılıklarını arttırmalıyız.
	 * */
	
	public Chromosome selectionUsingRouletteWheel() {
		int[] weights = new int[this.chromosomeCount];
		int totalWeight = 0;
		for(int i = 0; i < this.chromosomeCount; i++) {
				int fitness = this.chromosomes[i].getFitnessScore();
				int weight = this.genCount - fitness; // Kromozom ile şifrenin kaç karakterlerinin aynı olduğunun sayısı
				weights[i] = weight;
				totalWeight += weight;
		}
		
		int rand = random.nextInt(totalWeight+1);
		int partialSum = 0;
		for(int i = 0; i < this.chromosomeCount; i++) {
			partialSum += weights[i];
			if(partialSum >= rand) {
				return chromosomes[i];
			}
		}
		return null;
	}
	
	public Chromosome crossOver(Chromosome selected1, Chromosome selected2) {
		int genCount = selected1.getGenCount();
		Chromosome newChromosome = new Chromosome(genCount);
        int geneA = (int) (Math.random() * genCount);
        int geneB = (int) (Math.random() * genCount);
        
        int startGene = Math.min(geneA, geneB);
        int endGene = Math.max(geneA, geneB);

        for(int i = 0; i < genCount; i++) {
        	if(i < endGene && i > startGene) {  // Bu aralıkta genlerini selected1 kromozomundan alır
        		newChromosome.getGenes()[i] = selected1.getGenes()[i];
        	}
        	else {
        		newChromosome.getGenes()[i] = selected2.getGenes()[i];
        	}
        }
        return newChromosome;       
	}
	
	
	public void generateNextGeneration() { // Combines elitisim, selection and cross over
		Chromosome[] nextGeneration = new Chromosome[chromosomeCount];
		
		// Elitisim - Copies best chromosome(solution) to the next generation
		nextGeneration[0] = elitism();
		
		// Makes selection using roulette wheel and cross over selected chromosomes untill next generation reaches chromosome count
		for(int i = 1; i < chromosomeCount; i++) {
			Chromosome selected1 = selectionUsingRouletteWheel();
			Chromosome selected2 = selectionUsingRouletteWheel();
			
			Chromosome newChr = crossOver(selected1, selected2);
			newChr.calculateFitnessScore(password);
			nextGeneration[i] = newChr;
		}

		this.chromosomes = nextGeneration;
		this.generationNum++;
		
	}
	
	public void mutation() { // Her kromozomda %5 olasılıkla rastgele bir geni rastgele bir karakterle değiştirir.
		for(int i = 0; i < chromosomeCount; i++) {
			if(random.nextDouble() < this.MUTPROB) {
				int randIndex = random.nextInt(genCount);
				char randChar = characters[random.nextInt(characters.length)];
				this.chromosomes[i].getGenes()[randIndex] = randChar;
				this.chromosomes[i].setFitnessScore(0);
				this.chromosomes[i].calculateFitnessScore(password);
			}
		}
	}
	
}
