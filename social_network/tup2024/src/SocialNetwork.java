import java.util.*;

public class SocialNetwork {
    private List<Usuario> usuarios;
    private Set<String> visitados = new HashSet<>();
    private Stack<Usuario> stack = new Stack<>();

    public SocialNetwork() { this.usuarios = new ArrayList<>(); }

    public void addUser(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario findUserById(int id) {
        for (Usuario user : usuarios) {
            if (id == user.getId())
                return user;
        }
        return null;
    }

    public List<Usuario> bfs(int id) {
        Usuario inicio = findUserById(id);
        if (inicio == null)
            return null;

        List<Usuario> resultado = new ArrayList<>();
        Set<Usuario> visitados = new HashSet<>();
        Queue<Usuario> queue = new LinkedList<>();
        queue.add(inicio);
        visitados.add(inicio);

        while (!queue.isEmpty()) {
            Usuario actual = queue.poll();
            resultado.add(actual);

            for (Usuario amigo : actual.getFriends()) {
                if (!visitados.contains(amigo)) {
                    queue.add(amigo);
                    visitados.add(amigo);
                }
            }
        }
        return resultado;
    }

    public List<Usuario> dfs(int id) {
        Usuario inicio = findUserById(id);
        if (inicio == null)
            return null;

        List<Usuario> resultado = new ArrayList<>();
        Set<Usuario> visitados = new HashSet<>();
        Stack<Usuario> stack = new Stack<>();
        stack.push(inicio);
        visitados.add(inicio);

        while (!stack.isEmpty()) {
            Usuario actual = stack.pop();
            resultado.add(actual);

            for (Usuario amigo : actual.getFriends()) {
                if (!visitados.contains(amigo)) {
                    stack.push(amigo);
                    visitados.add(amigo);
                }
            }
        }
        return resultado;
    }

    public List<Usuario> mutualFriends(Usuario user1, Usuario user2) {
        List<Usuario> mutual = new ArrayList<>();
        for (Usuario friend : user1.getFriends()) {
            if (user2.getFriends().contains(friend))
                mutual.add(friend);
        }
        return mutual;
    }
}
