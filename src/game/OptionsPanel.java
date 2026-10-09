package game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel {
    public Boolean open = false;

    // GUI Elements
    private JPanel optionsPanel;
    private JPanel centerPanel;

    private JButton resumeButton;
    private JButton quitButton;

    // Volume
    private JSlider musicVolume;
    private JSlider fxVolume;
    private JLabel musicVolDisplay;
    private JLabel fxVolDisplay;



    // Notified Classes
    private GameManager gameManager;
    private GameMusic gameMusic;
    private SoundEffect soundEffect;

    public OptionsPanel(GameLevel world, GameView gameView) {

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.start();
                gameView.hideMenuOverlay();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fxVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                soundEffect.setCurrentVolume(source.getValue());
                fxVolDisplay.setText(String.valueOf(source.getValue()));
            }
        });
        musicVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                gameMusic.setCurrentVolume(source.getValue());
                musicVolDisplay.setText(String.valueOf(source.getValue()));

            }
        });

    }

    public JPanel getOptionsPanel(){
        return optionsPanel;
    }
}
