# 🕸️ Editor de Grafos y Rutas Óptimas

Una herramienta visual y matemática diseñada para la construcción, análisis y resolución de problemas topológicos mediante algoritmos clásicos de Teoría de Grafos y una interfaz **FlatLaf Dark**.

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)]()
[![Swing](https://img.shields.io/badge/GUI-Swing%20+%20FlatLaf-2C2C2C?style=for-the-badge&logo=java&logoColor=orange)]()





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

## ⚡ Instalación y Uso

1. **Clonar el repositorio:**
```bash
git clone [https://github.com/JesusShady/Editor-Grafos.git](https://github.com/JesusShady/Editor-Grafos.git)