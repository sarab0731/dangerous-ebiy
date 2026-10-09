package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tracker implements StepListener {
    private GameView view;
    private GameManager gameManager;
    private GameLevel currentLevel;
    private Character character;

    private Timer cameraTimer;
    private float targetX;
    private boolean isSliding = false; // Prevent multiple timers running

    public Tracker(GameView view,GameLevel currentLevel) {
        this.view = view;
        this.gameManager = currentLevel.getGameManager();
        this.currentLevel = currentLevel;
        this.character = gameManager.getPlayer();

    }

    public void preStep(StepEvent e) {}

    public void postStep(StepEvent e) {
        float playerX = character.getPosition().x;

        int numberOfPanels = currentLevel.getNumberOfPanels();
        Vec2[] viewCenters = view.generateViewCenters(numberOfPanels);

        if (viewCenters.length == 0) return; // No view centers available

        float viewportWidth = (float) view.getWidth() /view.getZoom();
        float commonArea = viewportWidth * (96f / 325f);
        float currentCenterX = view.getCentre().x;

        float rightThreshold = currentCenterX + viewportWidth/2 - (character.halfWidth*4);
        float leftThreshold = currentCenterX - viewportWidth/2 + (character.halfWidth*4);

        float rightTargetX = currentCenterX + (viewportWidth + commonArea)/2;  // Move right
        float leftTargetX = currentCenterX - (viewportWidth + commonArea)/2;   // Move left

        if (!isSliding) {
            if (playerX >= rightThreshold && (currentCenterX < viewCenters[numberOfPanels - 1].x)) {
                // Only move if there are more panels to the right
                smoothMoveView(rightTargetX);
            } else if (playerX <= leftThreshold && (currentCenterX > viewCenters[0].x)) {
                // Only move if there are more panels to the left
                smoothMoveView(leftTargetX);
            }
        }
    }

    // Smoothly moves the camera
    private void smoothMoveView(float newTargetX) {
        targetX = newTargetX;

        cameraTimer = new Timer(16, new ActionListener() { // ~60 FPS
            float currentX = view.getCentre().x;
            int steps = 20; // Number of steps for smooth transition
            float stepX = (targetX - currentX) / steps;
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < steps) {
                    currentX += stepX;
                    view.setCentre(new Vec2(currentX, view.getCentre().y));
                    count++;
                } else {
                    view.setCentre(new Vec2(targetX, view.getCentre().y)); // Final position
                    isSliding = false;
                    cameraTimer.stop();
                }
            }
        });

        cameraTimer.start();
    }

    public void updatePlayer(Character newCharacter){ // called when character loses a life or progresses onto a new level
        character = newCharacter;
    }

    public void updateTracker(GameLevel newLevel) {
        this.currentLevel = newLevel;
        this.character = newLevel.getGameManager().getPlayer();
    }


}