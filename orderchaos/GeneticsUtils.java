package WorldSimulation.orderchaos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GeneticsUtils {

	public static final Set<String> allGeneNames = new HashSet<>(
			Arrays.asList("Hair red", "Hair green", "Hair blue", "Eyes red", "Eyes green", "Eyes blue", "Skin red",
					"Skin green", "Skin blue", "Wings", "Wings red", "Wings green", "Wings blue", "Tail", "Horns"));

	public GeneticsUtils() {
	}

	public Gene[] getGeneArray(Being being) {
		Gene[] genes = new Gene[10];
		// This is the gene for red hair.
		genes[0] = new Gene(10, 23, true);
		return null;
		// TODO: implement this better
	}

	public static Gene[] breedGenes(Gene[] mom, Gene[] dad) {
		Gene[] child = new Gene[allGeneNames.size()];
		int a;
		int b;
		boolean dom;
		int i = 0;
		for (String geneName : allGeneNames) {
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
			if (mom[i].isDom() == dad[i].isDom()) {
				dom = mom[i].isDom();
			}
			else {
				dom = true;
			}
			child[i] = new Gene(a, b, dom);
			child[i].setGeneName(geneName);
			i++;
		}
		return child;
	}
	
	public static String printGenes(Gene[] array) {
		String out = "";
		for (int i = 0; i < array.length; i++) {
			out = out + array[i].toString() + "\n\n";
		}
		return out;
	}
	
	public static Gene[] generateGenome() {
		Gene[] name = new Gene[allGeneNames.size()];
		int i = 0;
		for (String geneName : allGeneNames) {
			if (geneName.equals("Wings") || geneName.equals("Tail") || geneName.equals("Horns")) {
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
