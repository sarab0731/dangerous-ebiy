package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GameView extends UserView{
    private GameLevel world;

    private Timer objectTimer;

    private static final Image heartIcon = new ImageIcon("data/ui/heart.png").getImage();

    private static final Image gunIcon = new ImageIcon("data/collectables/gun.png").getImage();
    private static final Image jetpackIcon = new ImageIcon("data/collectables/jetpack.png").getImage();
    private static final Image diamondIcon = new ImageIcon("data/collectables/diamond.png").getImage();
    private static final Image keyIcon = new ImageIcon("data/collectables/key.png").getImage();
    private static final Image beanieIcon = new ImageIcon("data/collectables/beanie.png").getImage();

    private JPanel overlay;


    public GameView(GameLevel w, int width, int height) {
        super(w, width, height);
        this.world = w;

        // Timer to move background objects
        objectTimer = new Timer(16, e -> { // 60 fps
            for (BackgroundObject obj : world.getBackgroundObjects()) {
                obj.move();
            }
            repaint();
            //System.out.println(world.getBackgroundObjects().get(0).getPosition()); //debug
        });
        objectTimer.start();

        //Menu Button
        this.setLayout(new BorderLayout());

        // Button on top-right
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("data/ui/settings.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JButton button = new JButton(imageIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Top panel to hold the button aligned to the right
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // so the panel is transparent
        topPanel.add(button, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.stop();
                showMenuOverlay();
            }
        });



    }

    @Override
    protected void paintBackground(Graphics2D g) {
        super.paintBackground(g);
        g.drawImage(world.getBackgroundImage(), 0, 0, this);
        for (BackgroundObject obj : world.getBackgroundObjects()) {
            obj.render(g);
        }

    }

    public Vec2[] generateViewCenters(int numberOfPanels) {
        Vec2[] viewCenters = new Vec2[numberOfPanels]; // Create array based on panel count
        float startX = 0;  // Initial X position
        float viewportWidth = (float) this.getWidth() / this.getZoom();
        float commonArea = viewportWidth * (96f / 325f);
        float currentCenterX = this.getCentre().x;

        float spacing = currentCenterX + (viewportWidth + commonArea) / 2;  // Space between panels

        for (int i = 0; i < numberOfPanels; i++) {
            viewCenters[i] = new Vec2(startX + (i * spacing), 0); // Adjust positions
        }
        return viewCenters;
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);

        // get game stats
        GameManager gameManager = world.getGameManager();
        int score = gameManager.getScore();
        int lives = gameManager.getLives();

        // font + color
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.WHITE);

        // score
        g.drawString("Score: " + score, 20, 40);

        // Draw lives as heart icons
        for (int i = 0; i < lives; i++) {
            g.drawImage(heartIcon, 20 + (i * 30), 70, 25, 25, this);
        }

        // Draw collectable

        int x = 1000, y = 50;
        int iconWidth = 40;
        int iconHeight = 35;
        int spacing = 20;

        if (gameManager.hasGun()) {
            g.drawImage(gunIcon, x, y,iconWidth,iconHeight, null);
            x += (iconWidth+ spacing);
        }

        if (gameManager.hasJetpack()) {
            g.drawImage(jetpackIcon, x, y,iconWidth,iconHeight, null);
            x += (iconWidth+ spacing);

            // Draw jetpack progress bar
            int barWidth = 200;
            int filledWidth = (int) (barWidth * (Math.min(score, 100) / 100));
            g.setColor(Color.GRAY);
            g.fillRect(20, 50, barWidth, 10);
            g.setColor(Color.GREEN);
            g.fillRect(20, 50, filledWidth, 10);


        }

        if (gameManager.hasKey()) {
            g.drawImage(keyIcon, x, y, iconWidth,iconHeight, null);
            x += (iconWidth+ spacing);
        }

    }

    public JPanel getOverlay() {
        return overlay;
    }

    public void setOverlay(JPanel overlay) {
        this.overlay = overlay;
    }

    public void showMenuOverlay() {
        if (overlay != null && overlay.getParent() == this) {
            return;
        }

        overlay = new JPanel();
        overlay.setLayout(new GridBagLayout()); // to center the menu
        overlay.setBounds(0, 0, getWidth(), getHeight()); // fill the GameView
        overlay.setOpaque(false);

        // Options panel in center
        OptionsPanel optionsPanel = new OptionsPanel(world, this);
        overlay.add(optionsPanel.getOptionsPanel()); // add your custom panel

        // Add overlay on top
        setLayout(null); // allow absolute positioning
        add(overlay, Integer.valueOf(1)); // ensure it's in front
        overlay.repaint();
        overlay.revalidate();
    }

    public void hideMenuOverlay() {
        if (overlay != null) {
            remove(overlay);
            overlay = null;
            revalidate();
            repaint();
        }
    }

    public void setGameLevel(GameLevel newLevel) {
        this.world = newLevel;
    }
}