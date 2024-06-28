import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int counter = 0;
    private int id;
    private String nombre;
    private List<Usuario> friends;  

    // para que se cree automaticamente el id y las amistades sean bidireccionales
    public Usuario(String nombre) {
        this.id = ++counter;
        this.nombre = nombre;
        this.friends = new ArrayList<>();
    }

    public int getId() { return id; }
    public List<Usuario> getFriends() { return friends; }
    public String getNombre() { return nombre; }

    public void addFriend(Usuario friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            friend.getFriends().add(this); // amistad mutua
        }
    }
}
