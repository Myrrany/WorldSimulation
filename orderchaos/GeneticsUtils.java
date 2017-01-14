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

	public Gene[] getGeneArray() {
		Gene[] genes = new Gene[10];
		// This is the gene for red hair.
		genes[0] = new Gene(10, 23, true);
	}
}
