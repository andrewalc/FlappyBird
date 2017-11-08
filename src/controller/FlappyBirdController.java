package controller;

import model.GameState;
import view.FlappyBirdView;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for FlappyBird.
 */
public class FlappyBirdController {
    private GameState state;
    private FlappyBirdView view;

    private int tickCount = 0;


    public FlappyBirdController(GameState state, FlappyBirdView view){
        this.state = state;
        this.view = view;
        view.updateView(state, 0);
        configureFBKeyListener();
    }

    private void configureFBKeyListener(){
        // Map for Keys to commands
        Map<Integer, Runnable> keyPresses = new HashMap<Integer, Runnable>();

        // Fire a bullet.
        keyPresses.put(KeyEvent.VK_SPACE, () -> {
            state.getPlayer().keyMove(KeyEvent.VK_SPACE);
        });

        FBKeyListener kl = new FBKeyListener();
        kl.setKeyPressedMap(keyPresses);
        view.addKeyListener(kl);
    }

    public void startFlappyBird(){
        view.initialize();
        while(!state.isGameOver()){
        	//System.out.println(state.getPlayer().getVelocity());
        	
            Instant start = Instant.now();

            state.onTick(tickCount);
            view.updateView(state, tickCount);
            Instant after = Instant.now();
            while(Duration.between(start, after).toMillis() < 1000/30.0){
                after = Instant.now();
            }

            tickCount++;
        }
    }


    public int getTickCount() {
        return tickCount;
    }
}
