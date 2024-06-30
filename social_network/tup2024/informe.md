# Informe del Proyecto: Red Social Básica

## Descripción del Proyecto

El proyecto desarrollado es una Red Social Básica en Java que permite representar usuarios como nodos y las relaciones entre ellos como aristas.
Además, implementa algoritmos de búsqueda en anchura (BFS) y profundidad (DFS) para encontrar conexiones entre usuarios. El proyecto se divide en
varios componentes, cada uno responsable de diferentes funcionalidades del sistema.

## Estructura del Proyecto
El proyecto está organizado en diferentes carpetas y archivos, cada uno con una responsabilidad específica:

**Components:** Contiene componentes personalizados para la interfaz de usuario.

**Verificaciones:** Contiene una clase de validación para buscar usuarios.
Archivos principales: Clases para manejar la lógica de la red social y la interfaz de usuario.

### Archivos en la carpeta Components
**CustomPanel.java:**
Esta clase define un panel personalizado con bordes redondeados y un color de fondo específico.
Utiliza Graphics2D para el renderizado con antialiasing para un mejor aspecto visual.

**CustomSearchTextField.java:**
Esta clase define un campo de texto personalizado también con bordes redondeados y un color de fondo específico.
Es utilizado para la barra de búsqueda de usuarios en la red social.

**RoundedBtn.java:**
Esta clase define un botón personalizado con bordes redondeados que cambia de color cuando el ratón pasa sobre él.
Esto mejora la experiencia de usuario al interactuar con los elementos de la interfaz.

### Archivos en la carpeta Verificaciones
**Validation.java:**
Esta clase contiene una validación que permite buscar usuarios en una lista. Utiliza un KeyListener para filtrar
y seleccionar usuarios a medida que se escribe en el campo de búsqueda.

### Archivos Principales
**SocialNetwork.java:**
Esta clase maneja la lógica principal de la red social. Define la estructura del grafo utilizando
una lista de adyacencia para representar usuarios y sus relaciones. Incluye métodos para agregar usuarios,
establecer relaciones y buscar conexiones utilizando BFS y DFS.

**Usuario.java:**
Esta clase representa a un usuario en la red social. Contiene atributos como nombre y una lista de amigos.

**SocialNetUI.java:**
Esta clase maneja la interfaz de usuario de la red social. Utiliza los componentes personalizados definidos en la carpeta
Components para crear una interfaz gráfica interactiva. Permite a los usuarios visualizar perfiles, listas de amigos y buscar otros usuarios.

### Funcionamiento del Programa
**Representación de Usuarios y Relaciones:**
Los usuarios se representan como nodos en un **grafo**, y las relaciones entre ellos se representan como aristas.
La clase SocialNetwork maneja esta representación y proporciona métodos para gestionar los usuarios y sus conexiones.

**Búsqueda de Conexiones:**
La clase **SocialNetwork** implementa los algoritmos de búsqueda en anchura (BFS)
y búsqueda en profundidad (DFS) para encontrar conexiones entre usuarios.
Estos algoritmos permiten explorar las relaciones y determinar cómo están conectados los usuarios en la red.

**Interfaz de Usuario:**
La clase **SocialNetUI** crea la interfaz gráfica utilizando Swing y los componentes personalizados.
Los usuarios pueden interactuar con la interfaz para buscar otros usuarios, ver perfiles y listas de amigos.

**Validación de Búsqueda:**
La clase **Validation** proporciona una funcionalidad de búsqueda en tiempo real que filtra la lista de usuarios
a medida que se escribe en el campo de búsqueda, mejorando la experiencia de usuario.

### Conclusión
El proyecto implementa una red social básica en Java, cumpliendo con los objetivos de aprendizaje de
representar grafos y aplicar algoritmos de búsqueda en anchura y profundidad. La separación de la lógica de la red social y
la interfaz de usuario permite un diseño modular y fácil de mantener.
Los componentes personalizados mejoran la apariencia y la usabilidad de la interfaz gráfica.
