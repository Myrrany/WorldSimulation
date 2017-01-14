package WorldSimulation.orderchaos;

public class NoPartnerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7522457158009677903L;

	public NoPartnerException(String name) {
		super("No partner found found for " + name + ", a partner is needed for this");
	}
	
}
