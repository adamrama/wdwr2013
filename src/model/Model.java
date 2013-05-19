package model;

public class Model {
	private Result result;
	private Data data;

	public Model() {
		data = new Data();
	};

	public Result refresh(int mode, long[] aspirations, long[] weights) {
		try {
			//mode poza 1 i 2 
                        long[] asp = { 0, 0 };
			if (mode == 1) {
				long[] wgs = { 100, 1 };
				aspirations = asp;
				weights = wgs;
			} else if (mode == 2) {
				long[] wgs = { 1, 100 };
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
        
        
}
