
import controller.FlappyBirdController;
import model.GameState;
import view.FlappyBirdView;


public class FlappyBirdMain {
	// Main Method
	public static void main(String[] args) {
		GameState model = new GameState();
		FlappyBirdView view = new FlappyBirdView();

		FlappyBirdController controller = new FlappyBirdController(model, view);
		controller.startFlappyBird();
	}
}
