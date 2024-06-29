import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
        setTitle("Red Social");
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
        navPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
        navPanel.setBackground(Color.decode("#1D1B1E"));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        JPanel navContainer = new JPanel();
        navContainer.setOpaque(false);
        navContainer.setLayout(new BoxLayout(navContainer, BoxLayout.Y_AXIS));
        navContainer.setBorder(BorderFactory.createEmptyBorder(getHeight() / 10, 60, 0, 0));

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
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(getWidth(), getHeight() / 8));
        header.setBackground(Color.decode("#8d0aff"));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setOpaque(false);
        mainPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(getHeight() / 18, 55, 0, 0));
        header.add(logoPanel, BorderLayout.WEST);

        JLabel logoLabel = new JLabel("Label");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 38));
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoPanel.add(logoLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 35, 5));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(getHeight() / 18, 0, 0, 20));
        String[] iconPaths = {
                "images/home-icon.png",
                "images/profile-icon.png",
                "images/notification-icon.png",
                "images/search-icon.png"
        };
        for (String iconPath : iconPaths) {
            ImageIcon icon = new ImageIcon(Main.class.getResource(iconPath));
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            JLabel iconLabel = new JLabel(icon);
            optionsPanel.add(iconLabel);
        }
        header.add(optionsPanel, BorderLayout.EAST);
        mainPanel.add(header, BorderLayout.NORTH);

        // panel de lista de amigos
        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BorderLayout());
        friendsPanel.setBackground(Color.decode("#252426"));

        JLabel friendsLabel = new JLabel("Friends Online");
        friendsLabel.setForeground(Color.WHITE);
        friendsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        friendsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        friendsPanel.add(friendsLabel, BorderLayout.NORTH);

        JPanel friendsListPanel = new JPanel();
        friendsListPanel.setLayout(new GridLayout(5, 2, 10, 10));
        friendsListPanel.setBackground(Color.decode("#252426"));

        // Aquí cargamos las imágenes de perfil y los nombres de los amigos
        List<Usuario> friends = new ArrayList<>(); 
        ImageIcon profileIcon = new ImageIcon(new ImageIcon("practico_5_red\\social_network\\tup2024\\src\\images\\profile-icon.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        for (int i = 0; i < 10; i++) {
            JPanel friendPanel = new JPanel();
            friendPanel.setBackground(Color.decode("#252426"));
            friendPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel profilePic = new JLabel(profileIcon);
            JLabel friendName = new JLabel("Friend " + (i + 1)); // Aquí debes obtener el nombre real del amigo
            friendName.setForeground(Color.WHITE);

            friendPanel.add(profilePic);
            friendPanel.add(friendName);
            friendsListPanel.add(friendPanel);
        }

        friendsPanel.add(friendsListPanel, BorderLayout.CENTER);
        mainPanel.add(friendsPanel, BorderLayout.CENTER);

        usersList = new JList<>();
        profilePic = new JLabel();
        friendsList = new JList<>();
        searchField = new JTextField();

        sendBtn = new RoundedBtn("Enviar");
        searchPanel.add(sendBtn);

        setVisible(true);
    }
}