package model;

public class Model {
	private Result result;
	private Data data;
	
	public Model(){
		data = new Data();
		long[] aspirations = {2,2};
		long[] weights = {2,2};
		refresh(aspirations,weights);
	};

	public Result refresh(long[] aspirations, long[] weights) {
		try {
			AmplCommunicationTools.getResults(data, aspirations, weights ,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
