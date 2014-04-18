package src;

import java.util.ArrayList;
import java.util.List;

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

	public void addModels(List<CartsModel> locm) {
		cm.addAll(locm);
	}

	public int numberOfPartitions() {
		return lop.size();
	}
	
	public void prettyPrint() {
		
	}
	
	public String toString() {
		
	}

}