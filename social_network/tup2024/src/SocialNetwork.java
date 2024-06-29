import java.util.*;

public class SocialNetwork {
    private List<Usuario> usuarios;

    public SocialNetwork() { this.usuarios = new ArrayList<>(); }

    public void addUser(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario getUserById(int id) {
        for (Usuario user : usuarios) {
            if (id == user.getId())
                return user;
        }
        return null;
    }

    public List<Usuario> bfs(int id) {
        Usuario inicio = getUserById(id);
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
        Usuario inicio = getUserById(id);
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

    public List<Usuario> findShortestPath(int startId, int endId) {
        Usuario start = getUserById(startId);
        Usuario end = getUserById(endId);
        if (start == null || end == null)
            return null;

        Queue<Usuario> queue = new LinkedList<>();
        Map<Usuario, Usuario> previous = new HashMap<>();
        Set<Usuario> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Usuario current = queue.poll();
            if (current.equals(end)) return constructPath(previous, start, end);

            for (Usuario friend : current.getFriends()) {
                if (!visited.contains(friend)) {
                    queue.add(friend);
                    visited.add(friend);
                    previous.put(friend, current);
                }
            }
        }
        return null;
    }

    public List<Usuario> constructPath(Map<Usuario, Usuario> previous, Usuario start, Usuario end) {
        List<Usuario> path = new LinkedList<>();
        for (Usuario at = end; at != null; at = previous.get(at)) {
            path.add(0, at);
        }
        if (path.get(0).equals(start))
            return path;
        return null;
    }

    public String shortestPathToStr(int startId, int endId) {
        List<Usuario> path = findShortestPath(startId, endId);
        if (path == null) return getUserById(startId).getNombre() + " y " + getUserById(endId).getNombre() + " no son amigos y no hay ruta para conectar";
        else if (path.size() == 2) return getUserById(startId).getNombre() + " y " + getUserById(endId).getNombre() + " no son amigos, pero tiene un amigo en común: " + path.get(1).getNombre();
        else return "";
    }
}
