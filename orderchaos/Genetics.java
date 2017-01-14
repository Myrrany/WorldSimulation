package WorldSimulation.orderchaos;


//  https://codeshare.io/5RbmwV

import java.util.HashMap;
import java.util.Random;

public class Genetics {
	
	// Recessive genes (wings, tails, etc.): 1 for no, 2 for yes.
	// RGB values: three genes, all between 0 and 255.
	
	// Everything for hair
	private int[] hairred;
	private int[] hairgreen;
	private int[] hairblue;
	
	// Everything for eyes
	private int[] eyesred;
	private int[] eyesgreen;
	private int[] eyesblue;
	
	// Everything for skin
	private int[] skinred;
	private int[] skingreen;
	private int[] skinblue;
	
	// Wings
	private int[] wings;
	private int[] wingsred;
	private int[] wingsgreen;
	private int[] wingsblue;
	
	// Tail
	private int[] tail;
	
	// Horns
	private int[] horns;
	
	// Everything
//	public HashMap<String, Integer> genes;
	
//	public Genetics(Genetics mom, Genetics dad) {
//		this.genes.put("Hair red", (mom.genes.get("Hair red")+ dad.genes.get("Hair red"))/2);
//		this.genes.put("Hair green", (mom.genes.get("Hair green")+ dad.genes.get("Hair green"))/2);
//		this.genes.put("Hair blue", (mom.genes.get("Hair blue")+ dad.genes.get("Hair blue"))/2);
//		this.genes.put("Eyes red", (mom.genes.get("Eyes red") + dad.genes.get("Eyes red"))/2);
//		this.genes.put("Eyes green", (mom.genes.get("Eyes green") + dad.genes.get("Eyes green"))/2);
//		this.genes.put("Eyes blue", (mom.genes.get("Eyes blue") + dad.genes.get("Eyes blue"))/2);
//		this.genes.put("Skin red", (mom.genes.get("Skin red") + dad.genes.get("Skin red"))/2);
//		this.genes.put("Skin green", (mom.genes.get("Skin green") + dad.genes.get("Skin green"))/2);
//		this.genes.put("Skin blue", (mom.genes.get("Skin blue") + dad.genes.get("Skin blue"))/2);
//		this.genes.put("Wings", (mom.genes.get("Wings") + dad.genes.get("Wings"))/2);
//		this.genes.put("Wings red", (mom.genes.get("Wings red") + dad.genes.get("Wings red"))/2);
//		this.genes.put("Wings green", (mom.genes.get("Wings green") + dad.genes.get("Wings green"))/2);
//		this.genes.put("Wings blue", (mom.genes.get("Wings blue") + dad.genes.get("Wings blue"))/2);
//		this.genes.put("Tail", (mom.genes.get("Tail") + dad.genes.get("Tail"))/2);
//		this.genes.put("Horns", (mom.genes.get("Horns") + dad.genes.get("Horns"))/2);
//		
//	}

	public int randomGene(int[] genes) {
		int rnd = new Random().nextInt(genes.length);
	    return genes[rnd];
	}
	
	
}
