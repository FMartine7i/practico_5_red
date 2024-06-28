import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SocialNetUI extends JFrame {
    private SocialNetwork socialNetwork;
    private JPanel mainPanel;
    private JList<Usuario> usersList;
    private JLabel profilePic;
    private JList<Usuario> friendsList;
    private JTextField searchField;
    private RoundedBtn sendBtn;

    public SocialNetUI() {
        createUIComponents();
    }

    public void createUIComponents() {
        setTitle("Label");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.setColor(Color.decode("#252426"));
                graphics.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // ----------------------- Configuración paneles ---------------------------
        // panel de nav
        JPanel navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(getWidth() / 4, getHeight()));
        navPanel.setBackground(Color.decode("#1D1B1E"));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        JPanel navContainer = new JPanel();
        navContainer.setOpaque(false);
        navContainer.setLayout(new BoxLayout(navContainer, BoxLayout.Y_AXIS));
        navContainer.setBorder(BorderFactory.createEmptyBorder(getHeight() / 8, getWidth() / 15, 0, 0));

        navPanel.add(navContainer);
        mainPanel.add(navPanel, BorderLayout.WEST);

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.BLACK);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        JLabel messageLabel = new JLabel("");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messagePanel.add(messageLabel);

        String[] options = {"Home", "Explore", "Notifications", "Messages", "Display", "Settings", "..."};
        for (String option : options) {
            JLabel optionLabel = new JLabel(option);
            optionLabel.setForeground(Color.WHITE);
            optionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            optionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            optionLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    messageLabel.setText(option + " no tiene ninguna funcionalidad asignada.");
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    optionLabel.setFont(new Font("Arial", Font.PLAIN, 19)); // Aumentar tamaño
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    optionLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Restaurar tamaño
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    optionLabel.setForeground(Color.decode("#944FFE")); // Cambiar color al pasar el mouse
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    optionLabel.setForeground(Color.WHITE); // Restaurar color al salir el mouse
                }
            });
            navContainer.add(Box.createRigidArea(new Dimension(0, 25)));
            navContainer.add(optionLabel);
        }
        mainPanel.add(navPanel, BorderLayout.WEST);
        navPanel.add(Box.createVerticalGlue()); // Empujar el messagePanel al fondo
        navPanel.add(messagePanel);

        // header
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(getWidth(), getHeight() / 8));
        header.setBackground(Color.decode("#8d0aff"));
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setOpaque(false);
        mainPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        header.add(logoPanel, BorderLayout.WEST);

        JLabel logoLabel = new JLabel("Label");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 22));
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoPanel.add(logoLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        String[] iconPaths = {
                "/images/home-icon.png",
                "/images/profile-icon.png"
            };
        for (String iconPath : iconPaths) {
            ImageIcon icon = new ImageIcon(iconPath);
            JLabel iconLabel = new JLabel(icon);
            optionsPanel.add(iconLabel);
        }
        header.add(optionsPanel, BorderLayout.EAST);

        // panel de lista
        usersList = new JList<>();
        profilePic = new JLabel();
        friendsList = new JList<>();
        searchField = new JTextField();

        sendBtn = new RoundedBtn("Enviar");
        searchPanel.add(sendBtn);

        setVisible(true);
    }
}
