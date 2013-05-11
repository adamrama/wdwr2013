import model.Model;
import view.View;
import controller.Controller;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(model,view);
	}
}
