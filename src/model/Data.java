package model;

public class Data {
	static final int months = 3;
	static final int props = 4;
	static final int components = 2;
	static final int resources = 2;
	static final double prop[] = { 0.4, 0.3, 0.2, 0.1 };
	static final long costProp[][][] = {{{25,55,35},{45,65,30}},{{50,20,45},{30,35,50}},{{30,60,25},{55,45,25}},{{40,30,55},{20,60,45}}}; // [prop][component][month]
	static final double need[][] = {{0.2, 0.7},{0.8,0.3}}; //[resource][component]
	static final long supply[][] = {{600,700,550},{1400,900,1200}}; //[resource], [month]
	static final long contract[] = {1100, 1200};
	static final long storage = 150;
	static final long Max = 3000;
	static final double overstorage_cost = 0.15; //%
	
	double cost[][];  //[components][months]
	
	Data(){
		cost = initCost();
	}

	private double[][] initCost() {
		double[][] cst = new double[components][months];
		for(int i=0; i<props; i++)
		{
			for(int j=0; j<components; j++)
			{
				for(int k=0; k<months; k++)
				{
					cst[j][k] += prop[i] * costProp[i][j][k];
				}
			}
		}
		return cst;
	}
}
