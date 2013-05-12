package model;

import java.util.List;

public class Result {
	private long X[][]; // produkcja komponentu w miesiacu
	private double risk;
	private double cost;
	private String text;

	public Result(String textResult, List<Double> list) {
		this.text = textResult;
		long tmp[][] = {
				{ list.get(0).longValue(),
						list.get(2).longValue(),
						list.get(4).longValue() },
				{ list.get(1).longValue(),
						list.get(3).longValue(),
						list.get(5).longValue()} };
		this.X = tmp;
		this.cost = list.get(6);
		this.risk =  list.get(7);
	}

	public void setX(long x[][]) {
		X = x;
	}

	public long[][] getX() {
		return X;
	}

	public void setRisk(double risk) {
		this.risk = risk;
	}

	public double getRisk() {
		return risk;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
