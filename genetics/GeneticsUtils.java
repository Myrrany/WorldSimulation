package WorldSimulation.genetics;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class GeneticsUtils {

	// This set contains the names of all used genes.
	// It can be extended if needed by adding more names to the list.
	private static final LinkedHashSet<String> allGeneNames = new LinkedHashSet<>(
			Arrays.asList("Hair red", "Hair green", "Hair blue", "Eyes red", "Eyes green", "Eyes blue", "Skin red",
					"Skin green", "Skin blue", "Wings", "Wings red", "Wings green", "Wings blue", "Tail", "Horns", "Elf Ears", "Gender"));

	public GeneticsUtils() {
	}

	/**
	 * Method to derive a possible genome for a child from the genome of the
	 * parents.
	 *
	 * @param mom Gene[] of the mother
	 * @param dad Gene[] of the father
	 * @return Gene[] of the child
	 */
	public static Gene[] breedGenes(Gene[] mom, Gene[] dad) {
		Gene[] child = new Gene[allGeneNames.size()];
		// Name some variables for later use.
		int a;
		int b;
		boolean dom;
		// i is not used to iterate over in the for-loop,
		int i = 0;
		// Foreach-loop goes over the genenames in allGeneNames and finds the
		// corresponding genes in the genome of the parents
		for (String geneName : allGeneNames) {
			// Random chance decides which of the two genes a parent will pass
			// on.
			if (Math.random() > 0.5) {
				a = mom[i].getChromA();
			} else {
				a = mom[i].getChromB();
			}
			if (Math.random() > 0.5) {
				b = dad[i].getChromA();
			} else {
				b = dad[i].getChromB();
			}
			// Check if a gene is dominant and pass it on.
			dom = mom[i].isDom() != dad[i].isDom() || mom[i].isDom();
// Store all previously gathered information in a gene and put it in
			// the childs genome.
			child[i] = new Gene(a, b, dom);
			child[i].setGeneName(geneName);
			i++;
		}
		return child;
	}

	/**
	 * ToString method for entire Gene[].
	 *
	 * @param array Gene array that needs to be converted into String
	 * @return String
	 */
	public static String genesToString(Gene[] array) {
		StringBuilder sb = new StringBuilder();
		// Iterates over the given array
		for (Gene anArray : array) {
			// Uses the Gene.toString method and appends it to a string builder.
			sb.append(anArray.toString());
			// Adds newlines for readability
			sb.append("\n\n");
		}
		// Gives the end result
		return sb.toString();
	}

	/**
	 * Method to randomly generate a Gene[].
	 *
	 * @return Gene[]
	 */
	public static Gene[] generateGenome() {
		Gene[] name = new Gene[allGeneNames.size()];
		int i = 0;
		// Iterates over all genenames and generates a gene
		for (String geneName : allGeneNames) {
			// Uses other parameters for gene generation if the gene is for the
			// presence of certain body parts
			if (geneName.equals("Wings") || geneName.equals("Tail") || geneName.equals("Horns") || geneName.equals("Elf Ears") || geneName.equals("Gender")) {
				name[i] = new Gene(false, 0, 1);
			} else {
				name[i] = new Gene();
			}
			name[i].setGeneName(geneName);
			i++;
		}
		return name;
	}

}