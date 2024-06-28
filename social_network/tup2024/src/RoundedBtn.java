import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedBtn extends JButton {
    private static final long serialVersionUID = 1L;
    private Color normalBackground;
    private Color hoverBackground;
    private Color normalForeground;

    public RoundedBtn(String str) {
        super(str);
        normalBackground = Color.decode("#8d0aff");
        hoverBackground = Color.decode("#9f0fd4");
        normalForeground = Color.WHITE;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBackground(normalBackground);
        setForeground(normalForeground);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalBackground);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(getBackground());
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        Rectangle rectangle = getBounds();
        int x = (rectangle.width - fontMetrics.stringWidth(getText())) / 2;
        int y = (rectangle.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        graphics2D.setColor(getForeground());
        graphics2D.drawString(getText(), x, y);
        graphics2D.dispose();
        super.paintComponent(graphics);
    }

    @Override
    protected void paintBorder(Graphics graphics) {

    }
}
