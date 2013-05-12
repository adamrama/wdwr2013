package model;

public class Model {
	private Result result;
	private Data data;

	public Model() {
		data = new Data();
		result = refresh(1, null, null);
		//System.out.println(result.getText());
	};

	public Result refresh(int mode, long[] aspirations, long[] weights) {
		try {
			long[] asp = { 0, 0 };
			if (mode == 1) {
				long[] wgs = { 1, 0 };
				aspirations = asp;
				weights = wgs;
			} else if (mode == 2) {
				long[] wgs = { 0, 1 };
				aspirations = asp;
				weights = wgs;
			}
			result = AmplCommunicationTools.getResults(data, aspirations, weights);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
