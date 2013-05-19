package controller;

import model.AmplCommunicationTools;
import view.NewApplication;
import model.Model;
import model.Result;

public class Controller {
	public Controller(Model model, NewApplication view) {
		this.model = model;
		this.view = view;
	}
	private Model model;
	private NewApplication view;
        
        public Result refresh(int mode, long[] aspirations, long[] weights) {
            return model.refresh(mode, aspirations, weights);
        }
        
        public String returnText(){
            return model.getResult().getText();
        }
        
        public String getNormalText(){
            StringBuilder sp = new StringBuilder();
            sp.append("Wynik jest następujący:\n");
            sp.append("koszt: ").append(model.getResult().getCost()).append("\n");
            sp.append("ryzyko: ").append(model.getResult().getRisk()).append("\n");
            sp.append("Produkcja komponentu w miesiacu:\n");
            for(int i = 0; i < model.getResult().getX().length; i++){
                for(int j = 0; j < model.getResult().getX()[i].length; j++){
                    sp.append(model.getResult().getX()[i][j]).append(" ");
                }
                sp.append("\n");
            }
            return sp.toString();
        }
        
        
}
