package controller;

import view.View;
import model.Model;

public class Controller {
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	private Model model;
	private View view;

}
