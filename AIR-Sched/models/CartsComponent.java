package models;

public class CartsComponent {

	private String name;
	private String algo;
	private int criticality;
	private int period;
	private int execution;
	private double bandwith;

	public CartsComponent(String name, String algo, int criticality,
			int period, int execution, double bandwith) {
		this.name = name;
		this.algo = algo;
		this.criticality = criticality;
		this.period = period;
		this.execution = execution;
		this.bandwith = bandwith;
	}

	public String getName() {
		return name;
	}

	public String getAlgo() {
		return algo;
	}

	public int getCriticality() {
		return criticality;
	}

	public int getPeriod() {
		return period;
	}

	public int getExecution() {
		return execution;
	}

	public double getBandwith() {
		return bandwith;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("*** partition " + name + " ***\n");
		str.append("alg : " + algo + "\n");
		str.append("crit: " + criticality + "\n");
		str.append("pri : " + period + "\n");
		str.append("exc : " + execution + "\n");
		str.append("bw  :  " + bandwith + "\n");
		return str.toString();
	}

	public void incrementExecution() {
		this.execution++;
	}

	public void incrementExecution(int i) {
		this.execution += i;
	}

}
