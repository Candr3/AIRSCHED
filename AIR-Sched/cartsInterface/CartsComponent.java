package cartsInterface;

public class CartsComponent {

	private String name;
	private String algo;
	private int period;
	private int execution;
	private double bandwith;

	public CartsComponent(String name, String algo, int period, int execution,
			double bandwith) {
		this.name = name;
		this.algo = algo;
		this.period = period;
		this.execution = execution;
		this.bandwith = bandwith;
	}
	
}
