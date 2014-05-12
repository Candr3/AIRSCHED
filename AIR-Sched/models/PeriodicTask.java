package models;

import deprecated.MathUtils;

public class PeriodicTask {

	private String name;
	private int id;
	private int period;
	private int capacity;

	public PeriodicTask(String name, int id, int period, int capacity) {
		this.name = name;
		this.id = id;
		this.period = period;
		this.capacity = capacity;
	}

	public double getTaskDBF(int t) {
		return MathUtils.lowerBoundDivision(t, period) * capacity;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("*nome : " + name + "\n");
		str.append("*id   : " + id + "\n");
		str.append("*perio: " + period + "\n");
		str.append("*wcet : " + capacity + "\n");
		return str.toString();
	}

	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
