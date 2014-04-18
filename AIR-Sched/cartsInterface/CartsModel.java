package cartsInterface;

import java.util.ArrayList;

public class CartsModel {

	private String algo;
	private int model_period;
	private int model_execution;
	private double model_bandwith;
	private ArrayList<CartsComponent> model_components;

	public CartsModel(String algo, int model_period, int model_execution,
			double model_bandwith) {
		this.algo = algo;
		this.model_period = model_period;
		this.model_execution = model_execution;
		this.model_bandwith = model_bandwith;
		this.model_components = new ArrayList<CartsComponent>();
	}

	public void addComponent(CartsComponent cc) {
		this.model_components.add(cc);
	}

	public String getAlgo() {
		return algo;
	}

	public int getModel_period() {
		return model_period;
	}

	public int getModel_execution() {
		return model_execution;
	}

	public double getModel_bandwith() {
		return model_bandwith;
	}

	public ArrayList<CartsComponent> getModel_components() {
		return model_components;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("*** system ***\n");
		str.append("per: " + model_period + "\n");
		str.append("exe: " + model_execution + "\n");
		str.append("bw:  " + model_bandwith + "\n");
		for (CartsComponent cc : model_components)
			str.append(cc.toString());
		return str.toString();
	}

}