package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SchedSystem {

	private ArrayList<Partition> lop;
	private ArrayList<CartsModel> locm;

	public SchedSystem() {
		this.lop = new ArrayList<Partition>();
		this.locm = new ArrayList<CartsModel>();
	}

	public void addPartition(Partition p) {
		lop.add(p);
	}

	public void addModels(List<CartsModel> toadd) {
		locm.addAll(toadd);
	}

	public void addBestModel(List<CartsModel> toadd) {
		for (CartsModel cm : toadd) {
			if (locm.isEmpty()
					|| locm.get(0).getModel_bandwith() > cm.getModel_bandwith()) {
				locm.clear();
				locm.add(cm);
			}
		}
	}

	public int numberOfPartitions() {
		return lop.size();
	}

	public List<Partition> getPartitions() {
		return lop;
	}

	public Partition getPartition(int index) {
		return lop.get(index);
	}

	public List<CartsModel> getModels() {
		return locm;
	}

	public CartsModel getModel(int index) {
		return locm.get(index);
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("*** Partitions ***\n\n");
		for (Partition p : lop) {
			str.append(p.toString());
			str.append("\n");
		}
		str.append("*** Models ***\n\n");
		for (CartsModel cm : locm) {
			str.append(cm.toString());
			str.append("\n");
		}
		return str.toString();
	}

}