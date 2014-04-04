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
	}

	public void addComponent(CartsComponent cc) {
		this.model_components.add(cc);
	}

}
