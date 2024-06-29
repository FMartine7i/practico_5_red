import components.CustomPanel;
import components.CustomSearchTextField;
import components.RoundedBtn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SocialNetUI extends JFrame {
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JList<Usuario> usersList;
    private JLabel profilePic;

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
            Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            icons[i] = new ImageIcon(newimg);
        }

        // Crear las opciones con iconos
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            ImageIcon icon = icons[i];

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
                    System.out.println(option + " no tiene ninguna funcionalidad asignada.");
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

        // --------------------------- header ----------------------------
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
            Image newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            JLabel iconLabel = new JLabel(icon);
            optionsPanel.add(iconLabel);
        }
        header.add(optionsPanel, BorderLayout.EAST);
        mainPanel.add(header, BorderLayout.NORTH);


        // ---------------------- panel de lista de amigos ------------------------

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
        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(newimg);

        // Ejemplo de creación de amigos (simulación)
        Usuario usuario1 = new Usuario("Gianluca");
        Usuario usuario2 = new Usuario("Mila");
        Usuario usuario3 = new Usuario("Sergio");
        Usuario usuario4 = new Usuario("Aldi");
        Usuario usuario5 = new Usuario("Fede");
        Usuario usuario6 = new Usuario("Jessi");

        usuario1.addFriend(usuario2);
        usuario1.addFriend(usuario3);
        usuario1.addFriend(usuario5);
        usuario2.addFriend(usuario3);
        usuario4.addFriend(usuario6);
        usuario5.addFriend(usuario2);

        List<Usuario> amigosSimulados = new ArrayList<>();
        amigosSimulados.add(usuario1);
        amigosSimulados.add(usuario2);
        amigosSimulados.add(usuario3);
        amigosSimulados.add(usuario4);
        amigosSimulados.add(usuario5);
        amigosSimulados.add(usuario6);

        for (Usuario amigo : amigosSimulados) {
            JPanel friendPanel = new JPanel();
            friendPanel.setBackground(Color.decode("#252426"));
            friendPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel profilePicLabel = new JLabel(profileIcon);
            JLabel friendName = new JLabel(amigo.getNombre());
            friendName.setFont(new Font("Arial", Font.PLAIN, 16));
            friendName.setForeground(Color.WHITE);

            profilePicLabel.addMouseListener(new MouseAdapter() {
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

        // Añadir el cuadro de búsqueda
        JPanel searchFriendsPanel = new JPanel();
        searchFriendsPanel.setBackground(Color.decode("#141315"));
        searchFriendsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel searchLabel = new JLabel("Buscar amigo:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchFriendsPanel.add(searchLabel);

        searchTextField = new CustomSearchTextField(20);
        searchTextField.setBackground(Color.MAGENTA);
        searchFriendsPanel.add(searchTextField);

        RoundedBtn searchButton = new RoundedBtn("Buscar");
        searchButton.addActionListener(e -> buscarAmigo(usuario1)); // Usar usuario1 como ejemplo
        searchFriendsPanel.add(searchButton);

        mainPanel.add(searchFriendsPanel, BorderLayout.SOUTH);

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
        RoundedBtn searchButton = new RoundedBtn("Buscar");

        // Acción del botón de búsqueda
        searchButton.addActionListener(e -> {
            String nombreBuscar = searchField.getText().trim();
            boolean encontrado = false;
            for (Usuario amigo : usuario.getFriends()) {
                if (amigo.getNombre().equalsIgnoreCase(nombreBuscar)) {
                    encontrado = true;
                    break;
                }
            }
            if (encontrado)
                JOptionPane.showMessageDialog(this, nombreBuscar + " es amigo de " + usuario.getNombre(),
                        "Búsqueda de Amigo", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, nombreBuscar + " no es amigo de " + usuario.getNombre(),
                        "Búsqueda de Amigo", JOptionPane.ERROR_MESSAGE);
        });
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.SOUTH);

        // Mostrar el mensaje completo en un JOptionPane
        UIManager.put("OptionPane.background", Color.decode("#403C43"));    // personaliza el fondo del JoptionPane
        UIManager.put("Panel.background", Color.decode("#403C43"));
        JOptionPane.showMessageDialog(this, panel, "Lista de Amigos", JOptionPane.PLAIN_MESSAGE);
    }

    private void buscarAmigo(Usuario usuario) {
        String nombre = searchTextField.getText().trim();
        boolean encontrado = false;
        for (Usuario amigo : usuario.getFriends()) {
            if (amigo.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            UIManager.put("OptionPane.foreground", Color.WHITE);
            JOptionPane.showMessageDialog(this, nombre + " es amigo de " + usuario.getNombre(), "Búsqueda de Amigo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            UIManager.put("OptionPane.foreground", Color.WHITE);
            JOptionPane.showMessageDialog(this, nombre + " no es amigo de " + usuario.getNombre(), "Búsqueda de Amigo",
                    JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }
}