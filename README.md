# 🕸️ Editor de Grafos y Rutas Óptimas

Una herramienta visual y matemática diseñada para la construcción, análisis y resolución de problemas topológicos mediante algoritmos clásicos de Teoría de Grafos y una interfaz **FlatLaf Dark**.

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)]()
[![Swing](https://img.shields.io/badge/GUI-Swing%20+%20FlatLaf-2C2C2C?style=for-the-badge&logo=java&logoColor=orange)]()

<img width="665" height="441" alt="image" src="https://github.com/user-attachments/assets/de98b9f5-1bc4-4eea-9d0a-8c2295e33317" />

<img width="673" height="438" alt="image" src="https://github.com/user-attachments/assets/d6a5b06c-a884-44f3-8aff-e962bca2eeb1" />


---

## 🚀 Sobre el Proyecto

El **Editor de Grafos** es un entorno interactivo que facilita a estudiantes y desarrolladores la visualización de topologías de redes. A través del uso de Matrices de Adyacencia, el sistema permite evaluar estructuras complejas sin depender de componentes externos.

Combina la solidez de la Programación Orientada a Objetos en **Java Swing**, un diseño profesional en modo oscuro impulsado por **FlatLaf**, y un motor matemático para la relajación de aristas y búsqueda de caminos cortos.

## ✨ Características Principales

### 🧮 Motor de Algoritmos (Teoría de Grafos)
* **Búsqueda en Profundidad (DFS):** Exploración profunda de nodos utilizando recursividad.
* **Búsqueda en Amplitud (BFS):** Análisis por niveles apoyado en estructuras de Cola (Queue).
* **Algoritmo de Dijkstra:** Cálculo exacto del camino de costo mínimo desde un vértice origen.
* **Algoritmo de Prim:** Generación del Árbol Mínimo de Expansión (MST) para redes óptimas.

### 🎨 UX/UI Moderna y Profesional
* **Modo Oscuro Integrado:** Interfaz amigable para la vista mediante `FlatDarkLaf`.
* **Matriz Tabular:** Visualización automática y ordenada de las conexiones matemáticas en tiempo real.
* **Bitácora Detallada:** Consola con línea de tiempo y trazabilidad de cada acción ejecutada.

### 💾 Persistencia de Datos
* Guarda tus sesiones topológicas completas y recupéralas usando archivos `.grafo` serializados nativamente en Java.

---

## 🛠️ Tech Stack

| Componente | Tecnología | Descripción |
| :--- | :--- | :--- |
| **Lenguaje** | Java (JDK 17+) | Lógica matemática y POO pura. |
| **Frontend** | Swing + FlatLaf | Framework gráfico nativo mejorado con Look & Feel moderno. |
| **Estructuras** | Arrays & Collections | Manejo de matrices bidimensionales, Colas y Listas. |
| **Persistencia**| Java IO (Serialización) | Exportación de matrices y nodos en archivos locales binarios. |

---

### ⚙️ Configuración en Eclipse IDE

Si vas a evaluar o modificar este proyecto utilizando **Eclipse**, sigue estos pasos para evitar errores de dependencias con la interfaz gráfica:

1. Importa el proyecto en Eclipse (`File > Import > General > Projects from Folder or Archive` y selecciona la carpeta clonada).
2. **Configurar la librería visual (FlatLaf):**
   - Haz clic derecho sobre la carpeta del proyecto (`Editor-Grafos`) en el *Package Explorer*.
   - Selecciona `Build Path > Configure Build Path...`
   - Ve a la pestaña `Libraries`.
   - Haz clic en `Classpath` y luego en el botón `Add External JARs...` (o `Add JARs...` si ya está en tu workspace).
   - Busca y selecciona el archivo `flatlaf-3.5.4.jar` que se encuentra en la carpeta `/lib` de este repositorio.
   - Haz clic en `Apply and Close`.
3. Ve a la ruta `src/estructuras/InterfazGrafica.java`, haz clic derecho y selecciona `Run As > Java Application`.

   
👨‍💻 Autor-
Nombre: Francisco Fonseca, Rol: Desarrollo y Algoritmia,   GitHub: @SiraTop



## ⚡ Instalación y Uso

1. **Clonar el repositorio:**
```bash
git clone [https://github.com/JesusShady/Editor-Grafos.git](https://github.com/JesusShady/Editor-Grafos.git)
