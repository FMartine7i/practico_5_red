import components.CustomPanel;
import components.CustomSearchTextField;
import components.RoundedBtn;
import entities.SocialNetwork;
import entities.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SocialNetUI extends JFrame {
    private final SocialNetwork socialNetwork = new SocialNetwork();
    private JPanel mainPanel;

    public SocialNetUI() {
        addSampleUsers();
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
        setNavPanel();
        setHeader();
        setFriendsPanel();
    }

    // ----------------------- Configuración paneles ---------------------------

    // panel de nav
    public void setNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
        navPanel.setBackground(Color.decode("#1D1B1E"));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.BLACK);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        JLabel messageLabel = new JLabel("");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messagePanel.add(messageLabel);

        // ----------------------- Opciones del navPanel ---------------------------

        mainPanel.add(navPanel, BorderLayout.WEST); // Agregar navPanel directamente al mainPanel

        String[] options = {"Home", "Explore", "Notifications", "Messages", "Display", "Settings", "More"};
        // Mensajes específicos para cada opción
        String[] messages = {
                "Usted se encuentra en Home.",
                "Explorar lista de amigos.",
                "No tienes ninguna notificación nueva.",
                "No tienes ningún mensaje.",
                "Configuración de pantalla.",
                "Ajustes del sistema.",
                "Más opciones disponibles."
        };
        ImageIcon[] icons = {
                new ImageIcon(Main.class.getResource("images/home-icon.png")),
                new ImageIcon(Main.class.getResource("images/search-icon.png")),
                new ImageIcon(Main.class.getResource("images/notification-icon.png")),
                new ImageIcon(Main.class.getResource("images/message-icon.png")),
                new ImageIcon(Main.class.getResource("images/display-icon.png")),
                new ImageIcon(Main.class.getResource("images/settings-icon.png")),
                new ImageIcon(Main.class.getResource("images/more-icon.png"))
        };

        // Escalado de iconos
        for (int i = 0; i < icons.length; i++) {
            ImageIcon icon = icons[i];
            Image image = icon.getImage();
            Image newImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            icons[i] = new ImageIcon(newImg);
        }

        // Crear las opciones con iconos
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            ImageIcon icon = icons[i];
            String message = messages[i];

            JPanel optionPanel = new JPanel(new BorderLayout());
            optionPanel.setOpaque(true);
            optionPanel.setBackground(Color.decode("#1D1B1E"));
            optionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 0)); // Margen

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
            contentPanel.setOpaque(false);

            JLabel iconLabel = new JLabel(icon);
            JLabel optionLabel = new JLabel(option);

            iconLabel.setAlignmentY(Component.CENTER_ALIGNMENT); // Alinea verticalmente el icono al centro
            optionLabel.setAlignmentY(Component.CENTER_ALIGNMENT); // Alinea verticalmente el texto al centro

            contentPanel.add(iconLabel);
            contentPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio entre el ícono y el texto
            contentPanel.add(optionLabel);

            optionLabel.setForeground(Color.WHITE);
            optionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            optionPanel.add(contentPanel, BorderLayout.CENTER); // Agregar contentPanel al centro del optionPanel

            optionPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    UIManager.put("OptionPane.messageForeground", Color.WHITE);
                    UIManager.put("OptionPane.background", Color.decode("#403C43"));
                    UIManager.put("Panel.background", Color.decode("#403C43"));
                    JOptionPane.showMessageDialog(null, message);
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    optionLabel.setFont(new Font("Arial", Font.PLAIN, 17)); // Disminuir tamaño
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    optionLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Restaurar tamaño
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    optionPanel.setBackground(Color.decode("#433D45"));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    optionLabel.setForeground(Color.WHITE);
                    optionPanel.setBackground(Color.decode("#1D1B1E"));
                }
            });
            navPanel.add(optionPanel); // Agregar optionPanel directamente a navPanel
        }

        navPanel.add(Box.createVerticalGlue()); // Empujar el messagePanel al fondo
        navPanel.add(messagePanel);
    }

    // --------------------------- header ----------------------------

    public void setHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int w = getWidth();
                int h = getHeight();

                // Definir colores para el gradiente
                Color color1 = Color.decode("#8d0aff");
                Color color2 = Color.decode("#451489");

                // Crear gradiente lineal de arriba hacia abajo
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        header.setPreferredSize(new Dimension(getWidth(), getHeight() / 8));
        header.setBackground(Color.decode("#8d0aff"));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setOpaque(false);
        mainPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(getHeight() / 18, 45, 0, 0));
        header.add(logoPanel, BorderLayout.WEST);

        JLabel logoLabel = new JLabel("Red Social");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 32));
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoPanel.add(logoLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 35, 4));
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
            Image newImg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            JLabel iconLabel = new JLabel(icon);
            optionsPanel.add(iconLabel);
        }
        header.add(optionsPanel, BorderLayout.EAST);
        mainPanel.add(header, BorderLayout.NORTH);
    }

    // ---------------------- panel de lista de amigos ------------------------

    public void setFriendsPanel() {
        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BorderLayout());
        friendsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 0, 0));
        friendsPanel.setBackground(Color.decode("#252426"));

        JLabel friendsLabel = new JLabel("Friends Online");
        friendsLabel.setForeground(Color.WHITE);
        friendsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        friendsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        friendsPanel.add(friendsLabel, BorderLayout.NORTH);

        JPanel friendsListPanel = new JPanel();
        friendsListPanel.setLayout(new GridLayout(5, 2, 10, 5));
        friendsListPanel.setBackground(Color.decode("#252426"));

        // Cargar el icono de la foto de perfil y ajustar tamaño
        ImageIcon profileIcon = new ImageIcon("social_network/tup2024/src/images/profile-icon.png");
        Image image = profileIcon.getImage();
        Image newImg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(newImg);


        List<Usuario> amigosSimulados = socialNetwork.getAllUsers();
        for (Usuario amigo : amigosSimulados) {
            JPanel friendPanel = new JPanel();
            friendPanel.setBackground(Color.decode("#252426"));
            friendPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel profilePicLabel = new JLabel(profileIcon);
            JLabel friendName = new JLabel(amigo.getNombre());
            friendName.setFont(new Font("Arial", Font.PLAIN, 16));
            friendName.setForeground(Color.WHITE);

            friendPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mostrarListaDeAmigos(amigo);
                }
            });

            friendPanel.add(profilePicLabel);
            friendPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            friendPanel.add(friendName);
            friendsListPanel.add(friendPanel);
        }
        friendsPanel.add(friendsListPanel, BorderLayout.CENTER);
        mainPanel.add(friendsPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // -------------------------- Nueva ventana lista de amigos -----------------------------

    private void mostrarListaDeAmigos(Usuario usuario) {
        // Panel para mostrar la lista de amigos
        JPanel panel = new CustomPanel(15, 15, Color.decode("#1C1A1E"));
        panel.setLayout(new BorderLayout());

        // Construir la lista de amigos
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<h3 style='padding-left:20px'>Amigos de ").append(usuario.getNombre()).append("</h3>");
        sb.append("<ul>");
        for (Usuario amigo : usuario.getFriends()) {
            sb.append("<li>").append(amigo.getNombre()).append("</li>");
        }
        sb.append("</ul>");
        sb.append("</html>");

        // Crear el mensaje con la lista de amigos
        JLabel messageLabel = new JLabel(sb.toString());
        messageLabel.setForeground(Color.WHITE);
        panel.add(messageLabel, BorderLayout.CENTER);

        // Añadir el campo de búsqueda
        JPanel searchPanel = new CustomPanel(15, 5, Color.decode("#1C1A1E"));
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Buscar amigo:");
        CustomSearchTextField searchField = new CustomSearchTextField(15);
        searchField.setForeground(Color.WHITE);
        searchLabel.setForeground(Color.WHITE);
        JButton searchButton = createSearchButton(searchField, usuario);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        panel.add(searchPanel, BorderLayout.SOUTH);

        // Mostrar el mensaje completo en un JOptionPane
        UIManager.put("OptionPane.background", Color.decode("#403C43"));    // personaliza el fondo del JOptionPane
        UIManager.put("Panel.background", Color.decode("#403C43"));
        JOptionPane.showMessageDialog(this, panel, "Lista de Amigos", JOptionPane.PLAIN_MESSAGE);
    }

    // método para cambiar color del mensaje del JOptionPane
    public void showCustomMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
        UIManager.put("OptionPane.messageForeground", UIManager.getColor("OptionPane.messageForeground"));
    }

    // creación de amigos (simulación)
    public void addSampleUsers() {
        Usuario usuario1 = new Usuario("Gianluca");
        Usuario usuario2 = new Usuario("Mila");
        Usuario usuario3 = new Usuario("Sergio");
        Usuario usuario4 = new Usuario("Aldi");
        Usuario usuario5 = new Usuario("Fede");
        Usuario usuario6 = new Usuario("Jessi");

        usuario1.addFriend(usuario2);
        usuario1.addFriend(usuario3);
        usuario2.addFriend(usuario3);
        usuario4.addFriend(usuario6);
        usuario5.addFriend(usuario2);
        usuario5.addFriend(usuario6);

        List<Usuario> amigosSimulados = new ArrayList<>();
        amigosSimulados.add(usuario1);
        amigosSimulados.add(usuario2);
        amigosSimulados.add(usuario3);
        amigosSimulados.add(usuario4);
        amigosSimulados.add(usuario5);
        amigosSimulados.add(usuario6);

        // Agregar usuarios a la red social
        socialNetwork.addUser(usuario1);
        socialNetwork.addUser(usuario2);
        socialNetwork.addUser(usuario3);
        socialNetwork.addUser(usuario4);
        socialNetwork.addUser(usuario5);
        socialNetwork.addUser(usuario6);
    }

    // método creación del JButton con su acción correspondiente → buscar amigos del usuario
    private JButton createSearchButton(JTextField searchField, Usuario usuario) {
        RoundedBtn searchButton = new RoundedBtn("Buscar");
        String title = "Búsqueda de amigo";
        searchButton.addActionListener(e -> {
            String nombre = searchField.getText().trim();
            Usuario amigoBuscado = null;
            // busca al usuario en toda la red social
            for (Usuario user : socialNetwork.getAllUsers()) {
                if (user.getNombre().equalsIgnoreCase(nombre)) {
                    amigoBuscado = user;
                    break;
                }
            }

            if (searchField.getText().trim().isEmpty())
                showCustomMessageDialog(this, "No se ha ingresado ningún nombre", title, JOptionPane.ERROR_MESSAGE);
            else if (nombre.equalsIgnoreCase(usuario.getNombre()))
                showCustomMessageDialog(this, "No puedes ser tu propio amigo", title, JOptionPane.ERROR_MESSAGE);
            else {
            if (amigoBuscado != null) {
                if (usuario.getFriends().contains(amigoBuscado))
                    showCustomMessageDialog(this, nombre + " es amigo/a de " + usuario.getNombre(), title, JOptionPane.PLAIN_MESSAGE);
                else {
                    String msg = socialNetwork.shortestPathToStr(usuario.getId(), amigoBuscado.getId());
                    showCustomMessageDialog(this, msg, "Búsqueda de amigo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else showCustomMessageDialog(this, "No se encontró a " + nombre, title, JOptionPane.ERROR_MESSAGE);
            }
        });
        return searchButton;
    }
}