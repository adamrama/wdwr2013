package model;

public class Result {
		private int X[][];
		private double risk;
		private double cost;
		
		public void setX(int x[][]) {
			X = x;
		}
		public int[][] getX() {
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
		
		
}
