package WorldSimulation.orderchaos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GeneticsUtils {

	public static final Set<String> allGeneNames = new HashSet<>(
			Arrays.asList("hairred", "hairgreen", "hairblue", "eyesred", "eyesgreen", "eyesblue", "skinred",
					"skingreen", "skinblue", "wings", "wingsred", "wingsgreen", "wingsblue", "tail", "horns"));

	public GeneticsUtils() {
	}

	public Gene[] getGeneArray(Being being) {
		Gene[] genes = new Gene[10];
		// This is the gene for red hair.
		genes[0] = new Gene(10, 23, true);
		return null;
		// TODO: implement this better
	}

	public Gene[] breedGenes(Gene[] mom, Gene[] dad) {
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

}
