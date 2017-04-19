package WorldSimulation.locations;

public class World {
	
	public String worldname;
	public int humanpopulation;
	public int angelpopulation;
	public int demonpopulation;
	public int totalpopulation;

	public World(String name) {
		this.worldname = name;
		this.humanpopulation = 0;
		this.angelpopulation = 0;
		this.demonpopulation = 0;
		this.totalpopulation = 0;
	}
	
	
	
}
