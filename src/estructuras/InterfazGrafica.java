package estructuras;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InterfazGrafica extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grafo grafo;
    private JTextArea areaSalida;

    public InterfazGrafica() {
        setTitle("EDITOR DE GRAFOS");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- BARRA DE MENU ---
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemNuevo = new JMenuItem("Nuevo Grafo");
        JMenuItem itemAbrir = new JMenuItem("Abrir Archivo..."); 
        JMenuItem itemGuardar = new JMenuItem("Guardar Archivo..."); 
        JMenuItem itemSalir = new JMenuItem("Salir");
        
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemComoUsar = new JMenuItem("Manual de Usuario");
        JMenuItem itemTeoria = new JMenuItem("Referencia Teorica");

        menuArchivo.add(itemNuevo);
        menuArchivo.addSeparator();
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemGuardar);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        
        menuAyuda.add(itemComoUsar);
        menuAyuda.add(itemTeoria);
        
        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);

        // --- PANEL LATERAL ---
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(10, 1, 10, 10));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelBotones.setPreferredSize(new Dimension(270, 0));

        JLabel lblTitulo = new JLabel("HERRAMIENTAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setForeground(new Color(150, 150, 150));
        panelBotones.add(lblTitulo);

        JButton btnCrearArista = new JButton("Agregar Conexion");
        JButton btnMatriz = new JButton("Ver Matriz Adyacencia");
        JButton btnDFS = new JButton("Recorrido DFS");
        JButton btnBFS = new JButton("Recorrido BFS");
        JButton btnDijkstra = new JButton("Calcular Ruta (Dijkstra)");
        JButton btnPrim = new JButton("Arbol Minimo (Prim)"); 
        JButton btnLimpiar = new JButton("Limpiar Consola");
        
        btnCrearArista.putClientProperty("JButton.buttonType", "roundRect");
        btnCrearArista.setFont(btnCrearArista.getFont().deriveFont(Font.BOLD));
        
        btnDijkstra.setForeground(new Color(100, 180, 255));
        btnPrim.setForeground(new Color(150, 255, 150)); 
        btnLimpiar.setForeground(new Color(255, 100, 100));

        panelBotones.add(btnCrearArista);
        panelBotones.add(btnMatriz);
        panelBotones.add(new JSeparator());
        panelBotones.add(btnDFS);
        panelBotones.add(btnBFS);
        panelBotones.add(btnDijkstra);
        panelBotones.add(btnPrim); 
        panelBotones.add(new JSeparator());
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.WEST);

        // --- AREA CENTRAL  ---
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Consolas", Font.PLAIN, 14)); 
        areaSalida.putClientProperty("JComponent.roundRect", true);

        JScrollPane scrollPane = new JScrollPane(areaSalida);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        add(scrollPane, BorderLayout.CENTER);

        // --- EVENTOS ---
        itemNuevo.addActionListener(e -> iniciarNuevoGrafo());
        itemAbrir.addActionListener(e -> abrirGrafo());   
        itemGuardar.addActionListener(e -> guardarGrafo()); 
        itemSalir.addActionListener(e -> System.exit(0));
        
        btnCrearArista.addActionListener(e -> {
            if (grafo == null) { mostrarAlerta("⚠ Primero crea un grafo en: Archivo > Nuevo Grafo"); return; }
            agregarAristaDialogo();
        });
        
        btnMatriz.addActionListener(e -> { if(grafo!=null) imprimirMatrizEnGUI(); });
        btnDFS.addActionListener(e -> ejecutarAlgoritmo("DFS"));
        btnBFS.addActionListener(e -> ejecutarAlgoritmo("BFS"));
        btnDijkstra.addActionListener(e -> ejecutarAlgoritmo("Dijkstra"));
        btnPrim.addActionListener(e -> ejecutarAlgoritmo("Prim")); 
        btnLimpiar.addActionListener(e -> areaSalida.setText(""));
        
        itemComoUsar.addActionListener(e -> mostrarAyudaUso());
        itemTeoria.addActionListener(e -> mostrarTeoria());

        SwingUtilities.invokeLater(() -> imprimirLog("SISTEMA INICIADO", "Proyecto completado.\nSelecciona 'Archivo > Nuevo Grafo' para empezar."));
    }

    private void imprimirLog(String titulo, String contenido) {
        String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String separador = "──────────────────────────────────────────────────────────";
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(separador).append("\n");
        sb.append("  [").append(hora).append("]  ").append(titulo.toUpperCase()).append("\n");
        sb.append(separador).append("\n");
        sb.append(contenido).append("\n");
        
        areaSalida.append(sb.toString());
        areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
    }

    private void guardarGrafo() {
        if (grafo == null) { mostrarAlerta("No hay grafo para guardar."); return; }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Grafo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Grafo (*.grafo)", "grafo"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (!archivo.getName().endsWith(".grafo")) archivo = new File(archivo.getAbsolutePath() + ".grafo");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                oos.writeObject(grafo);
                imprimirLog("ARCHIVO GUARDADO", "Ruta: " + archivo.getAbsolutePath());
            } catch (IOException ex) {
                mostrarAlerta("Error al guardar: " + ex.getMessage());
            }
        }
    }

    private void abrirGrafo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir Grafo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Grafo (*.grafo)", "grafo"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                this.grafo = (Grafo) ois.readObject();
                imprimirLog("ARCHIVO CARGADO", "Nodos disponibles: " + grafo.getNodos());
                imprimirMatrizEnGUI(); 
            } catch (IOException | ClassNotFoundException ex) {
                mostrarAlerta("Error al abrir: " + ex.getMessage());
            }
        }
    }

    private void iniciarNuevoGrafo() {
        String input = JOptionPane.showInputDialog(this, "¿Cuantos nodos tendra el grafo?", "Nuevo Proyecto", JOptionPane.QUESTION_MESSAGE);
        if (input != null && !input.isEmpty()) {
            try {
                int n = Integer.parseInt(input);
                grafo = new Grafo(n);
                int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas nombrar los nodos manualmente?", "Configuracion", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < n; i++) {
                        String nombre = JOptionPane.showInputDialog("Nombre del nodo " + i + ":");
                        grafo.setInformacion(i, nombre != null ? nombre : "N" + i);
                    }
                } else {
                    for(int i=0; i<n; i++) grafo.setInformacion(i, "Nodo " + i);
                }
                imprimirLog("NUEVO GRAFO", "Grafo creado con " + n + " nodos.");
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error: Ingresa un numero válido.");
            }
        }
    }

    private void agregarAristaDialogo() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField txtOrigen = new JTextField();
        JTextField txtDestino = new JTextField();
        JTextField txtPeso = new JTextField("1");

        panel.add(new JLabel("Nodo Origen (ID):"));
        panel.add(txtOrigen);
        panel.add(new JLabel("Nodo Destino (ID):"));
        panel.add(txtDestino);
        panel.add(new JLabel("Peso (Costo):"));
        panel.add(txtPeso);

        int result = JOptionPane.showConfirmDialog(this, panel, "Nueva Conexión", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int u = Integer.parseInt(txtOrigen.getText());
                int v = Integer.parseInt(txtDestino.getText());
                int w = Integer.parseInt(txtPeso.getText());
                grafo.agregarArista(u, v, w);
                imprimirLog("CONEXIÓN AGREGADA", grafo.getInformacion(u) + " -> " + grafo.getInformacion(v) + " [Peso: " + w + "]");
            } catch (Exception ex) {
                mostrarAlerta("Error: Ingresa numeros enteros validos.");
            }
        }
    }

    private void imprimirMatrizEnGUI() {
        if (grafo == null) return;
        StringBuilder sb = new StringBuilder();
        int[][] matriz = grafo.getMatriz();
        int n = grafo.getNodos();
        String formatoHeader = "%-12s"; 
        String formatoCelda  = "%-12d";   

        sb.append(String.format(formatoHeader, " "));
        for (int i = 0; i < n; i++) {
            String nombre = grafo.getInformacion(i).toString();
            if (nombre.length() > 10) nombre = nombre.substring(0, 10);
            sb.append(String.format(formatoHeader, nombre));
        }
        sb.append("\n");

        for (int i = 0; i < n; i++) {
            String nombreFila = grafo.getInformacion(i).toString();
            if (nombreFila.length() > 10) nombreFila = nombreFila.substring(0, 10);
            sb.append(String.format(formatoHeader, nombreFila));
            for (int j = 0; j < n; j++) {
                sb.append(String.format(formatoCelda, matriz[i][j]));
            }
            sb.append("\n");
        }
        imprimirLog("MATRIZ DE ADYACENCIA", sb.toString());
    }

    private void ejecutarAlgoritmo(String tipo) {
        if (grafo == null) return;
        String input = JOptionPane.showInputDialog("ID del Nodo de Inicio:");
        if (input == null) return;
        try {
            int inicio = Integer.parseInt(input);
            String res = "";
            if(tipo.equals("DFS")) res = Algoritmos.recorridoDFS(grafo, inicio);
            else if(tipo.equals("BFS")) res = Algoritmos.recorridoBFS(grafo, inicio);
            else if(tipo.equals("Dijkstra")) res = Algoritmos.dijkstra(grafo, inicio);
            else if(tipo.equals("Prim")) res = Algoritmos.prim(grafo, inicio); 
            
            imprimirLog("RESULTADO " + tipo.toUpperCase(), res);
        } catch (Exception e) {
            mostrarAlerta("Error en ejecucion. Verifica el ID del nodo.");
        }
    }

    private void mostrarAyudaUso() {
        JOptionPane.showMessageDialog(this, "Guía:\n1. Archivo > Nuevo Grafo.\n2. Conecta nodos.\n3. Guarda tu progreso en Archivo > Guardar.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }
    
   
    private void mostrarTeoria() {
        String mensaje = "=== REFERENCIA TEORICA Y MANUAL DE BOTONES ===\n\n" +
                "1. CONCEPTOS BASICOS\n" +
                "• Grafo: Un grafo G=(V,E) es un par formado por un conjunto de nodos o vertices (V) y un conjunto de aristas o conexiones (E).\n" +
                "• Matriz de Adyacencia: Es la forma de representar el grafo. Usamos una matriz NxN donde la celda [i][j] vale 1 (o su peso) si hay arista entre los nodos, y 0 si no hay conexion.\n\n" +
                "2. FUNCIONAMIENTO DE LOS BOTONES (ALGORITMOS)\n\n" +
                "• Agregar Conexion (Arista):\n" +
                "  Crea el enlace entre un nodo origen y un destino. El 'peso' representa el costo, tiempo o distancia de esa conexion.\n\n" +
                "• Ver Matriz Adyacencia:\n" +
                "  Genera la vista tabular de las conexiones. Filas y columnas representan los nodos.\n\n" +
                "• Recorrido DFS (Busqueda en Profundidad):\n" +
                "  La idea principal es avanzar lo más profundo posible antes de retroceder. Util para explorar laberintos o topologias complejas.\n\n" +
                "• Recorrido BFS (Busqueda en Anchura):\n" +
                "  Su objetivo es visitar primero todos los vecinos directos de un vertice, luego los vecinos de esos vecinos, y así sucesivamente. Se usa mucho para encontrar el camino con menos saltos.\n\n" +
                "• Calcular Ruta (Dijkstra):\n" +
                "  Busca la distancia mínima (el camino mas corto y barato) desde un vertice origen a todos los demas vertices del grafo. Analiza los pesos de las aristas para decidir la ruta optima.\n\n" +
                "• Arbol Minimo de Expansion (Prim):\n" +
                "  A diferencia de Dijkstra que busca rutas entre dos puntos, Prim busca un Arbol de expansión con peso total minimo. Su meta es conectar TODOS los nodos del grafo asegurando que el costo total de la red sea el menor posible, sin crear ciclos cerrados.";

        JTextArea areaTexto = new JTextArea(mensaje);
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaTexto.setMargin(new Insets(15, 15, 15, 15)); 
        
        areaTexto.setBackground(new Color(40, 40, 40)); 
        areaTexto.setForeground(new Color(230, 230, 230));
        
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setPreferredSize(new Dimension(600, 450)); 
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JOptionPane.showMessageDialog(this, scrollPane, "Teoria de Grafos y Manual", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarAlerta(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Atencion", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        try { FlatDarkLaf.setup(); } catch( Exception ex ) { }
        SwingUtilities.invokeLater(() -> new InterfazGrafica().setVisible(true));
    }
}