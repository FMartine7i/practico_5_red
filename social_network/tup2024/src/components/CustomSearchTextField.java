package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CustomSearchTextField extends JTextField {
    private int arcWidth = 15;
    private int arcHeight = 15;

    public CustomSearchTextField(int columns) {
        super(columns);
        setOpaque(false);
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(5, 5, 5, 5))); // No visible border
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color
        g2.setColor(Color.decode("#403C43"));
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
