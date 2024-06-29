package components;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class CustomPanel extends JPanel {
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;

    public CustomPanel(int arcWidth, int arcHeight, Color backgroundColor) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
        setBorder(new EmptyBorder(new Insets(5, 5, 5, 5))); // No visible border
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border painting
    }

    @Override
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
}
