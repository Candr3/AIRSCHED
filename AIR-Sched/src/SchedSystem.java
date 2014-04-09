package src;

import java.util.ArrayList;

import cartsInterface.CartsModel;

public class SchedSystem {

	private ArrayList<Partition> lop;
	private ArrayList<CartsModel> cm;

	public SchedSystem() {
		this.lop = new ArrayList<Partition>();
		this.cm = new ArrayList<CartsModel>();
	}

	public void addPartition(Partition p) {
		lop.add(p);
	}
	
	public int numberOfPartitions() {
		return lop.size();
	}

}