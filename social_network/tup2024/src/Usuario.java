import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private List<Usuario> friends;

    public Usuario(List<Usuario> friends, String nombre, int id) {
        this.friends = friends;
        this.nombre = nombre;
        this.id = id;
    }

    public int getId() { return id; }
    public List<Usuario> getFriends() { return friends; }
    public String getNombre() { return nombre; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFriends(List<Usuario> friends) { this.friends = friends; }
}
