
public class Main {
	public static void main(String[] args) {
		int genCount1 = 17;  // ilk şifrenin uzunluğu
		int genCount2 = 7;  // ikinci şifrenin uzunluğu
		int chromosomeCount;
		int chromosomeCount1 = 50;
		int chromosomeCount2 = 100;
		int solutionCount = 3;

		String password1 = "ChatGPT and GPT-4";
		String password2 = "ChatGPT";
		
		int totalTime = 0;
		int totalGeneration = 0;
		long[] times = new long[2*solutionCount];
		int[] generations = new int[2*solutionCount];
		
		for(int i = 0; i < 2 * solutionCount; i++) { // password1 icin aynı islemler 50 ve 100 kromozom icin yapilacak.
			// İlk 3 cözümde kromozom sayısının 50, sonraki 3 cözümde kromozom sayısının 100 olması icin: 
			if(i < solutionCount)
				chromosomeCount = chromosomeCount1;
			else
				chromosomeCount = chromosomeCount2;
			
			long start = System.currentTimeMillis();
			Population population1 = new Population(chromosomeCount, genCount1, password1);
			population1.randomFirstGeneration();
			String bestPassword = population1.elitism().toString();
			while(!(bestPassword.equals(password1))) {
				System.out.println(population1.getGeneretionNum() + ". nesildeki cozume en yakin sonuc : " + bestPassword);
				population1.generateNextGeneration(); // it combines elitisim-selection-cross over
				population1.mutation(); // Mutate newly generated generation
				bestPassword = population1.elitism().toString();
			}
			long end = System.currentTimeMillis();
			//System.out.println((i+1) + ". cozum " + (end-start) + " milisaniyede ve " + population1.getGeneretionNum() + " nesilde bulundu.");
			times[i] = end - start;
			generations[i] = population1.getGeneretionNum();
			
		}
		
		// Sonuclarin yazdirilmasi:
		System.out.println("\n" + chromosomeCount1 + " kromozom kullanilarak " + password1 + " sifresi " + solutionCount + " defa cozuldu:");
		for(int i = 0; i < solutionCount; i++) {
			System.out.println((i+1) + ". cozum " + times[i] + " milisaniyede ve " + generations[i] + " nesilde bulundu.");
			totalGeneration += generations[i];
			totalTime += times[i];
		}		
		System.out.println(solutionCount + " cozum icin nesil ortalamasi: " + totalGeneration/solutionCount);
		System.out.println(solutionCount + " cozum icin sure ortalamasi(ms): " + (double)totalTime/solutionCount);
		
		
		totalTime = 0;
		totalGeneration = 0;
		System.out.println("\n" + chromosomeCount2 + " kromozom kullanilarak " + password1 + " sifresi " + solutionCount + " defa cozuldu:");
		for(int i = solutionCount; i < 2*solutionCount; i++) {
			System.out.println((i-2) + ". cozum " + times[i] + " milisaniyede ve " + generations[i] + " nesilde bulundu.");
			totalGeneration += generations[i];
			totalTime += times[i];
		}		
		System.out.println(solutionCount + " cozum icin nesil ortalamasi: " + totalGeneration/solutionCount);
		System.out.println(solutionCount + " cozum icin sure ortalamasi(ms): " + (double)totalTime/solutionCount);
		
		/* !NOT!: Ekran görüntüsü alabilmek adına "ChatGPT" şifresi için yukarıdaki kodu yeniden kullanıyorum.
		 * Konsol tüm kayıtları gösteremediği için bunu yapmak zorunda kaldım. Yoksa kod tekrarını önlemek için yukarıdaki kodda
		 * solutionCount'a göre if-else kullanarak Population nesnesine parametre olarak verdiğim chromosomeCount'u 
		 * değiştirdiğim gibi password için de aynısını yapardım.
		 * */
		
		int totalTime2 = 0;
		int totalGeneration2 = 0;
		long[] times2 = new long[2*solutionCount];
		int[] generations2 = new int[2*solutionCount];
		
		for(int i = 0; i < 2 * solutionCount; i++) { // password2 icin aynı islemler 50 ve 100 kromozom icin yapilacak.
			// İlk 3 cözümde kromozom sayısının 50, sonraki 3 cözümde kromozom sayısının 100 olması icin: 
			if(i < solutionCount)
				chromosomeCount = chromosomeCount1;
			else
				chromosomeCount = chromosomeCount2;
			
			long start = System.currentTimeMillis();
			Population population1 = new Population(chromosomeCount, genCount2, password2);
			population1.randomFirstGeneration();
			String bestPassword = population1.elitism().toString();
			while(!(bestPassword.equals(password2))) {
				System.out.println(population1.getGeneretionNum() + ". nesildeki cozume en yakin sonuc : " + bestPassword);
				population1.generateNextGeneration(); // it combines elitisim-selection-cross over
				population1.mutation(); // Mutate newly generated generation
				bestPassword = population1.elitism().toString();
			}
			long end = System.currentTimeMillis();
			//System.out.println((i+1) + ". cozum " + (end-start) + " milisaniyede ve " + population1.getGeneretionNum() + " nesilde bulundu.");
			times2[i] = end - start;
			generations2[i] = population1.getGeneretionNum();
			
		}
		
		// 50 kromozom için sonuclarin yazdirilmasi:
		System.out.println("\n" + chromosomeCount1 + " kromozom kullanilarak " + password2 + " sifresi " + solutionCount + " defa cozuldu:");
		for(int i = 0; i < solutionCount; i++) {
			System.out.println((i+1) + ". cozum " + times2[i] + " milisaniyede ve " + generations2[i] + " nesilde bulundu.");
			totalGeneration2 += generations2[i];
			totalTime2 += times2[i];
		}		
		System.out.println(solutionCount + " cozum icin nesil ortalamasi: " + totalGeneration2/solutionCount);
		System.out.println(solutionCount + " cozum icin sure ortalamasi(ms): " + (double)totalTime2/solutionCount);
		
		
		// 100 kromozom için sonuçların yazdırılması:
		totalTime2 = 0;
		totalGeneration2 = 0;
		System.out.println("\n" + chromosomeCount2 + " kromozom kullanilarak " + password2 + " sifresi " + solutionCount + " defa cozuldu:");
		for(int i = solutionCount; i < 2*solutionCount; i++) {
			System.out.println((i-2) + ". cozum " + times2[i] + " milisaniyede ve " + generations2[i] + " nesilde bulundu.");
			totalGeneration2 += generations2[i];
			totalTime2 += times2[i];
		}		
		System.out.println(solutionCount + " cozum icin nesil ortalamasi: " + totalGeneration2/solutionCount);
		System.out.println(solutionCount + " cozum icin sure ortalamasi(ms): " + (double)totalTime2/solutionCount);
		
		
		
	}
}
