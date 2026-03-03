package estructuras;

import java.io.Serializable;

public class Grafo implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    private int[][] Adyacentes;
    private Object[] Informacion;
    private int nodos;
    private boolean vacio = true;

    public Grafo(int numeroNodos) {
        this.nodos = numeroNodos;
        
        
        Adyacentes = new int[nodos][nodos]; 
        Informacion = new Object[nodos];
        
        
        // Inicializamos la matriz en 0
        for (int i = 0; i < nodos; i++) {
            for (int j = 0; j < nodos; j++) {
                Adyacentes[i][j] = 0; 
            }
        }
        vacio = false;
    }

    public void setInformacion(int indice, Object dato) {
        if (indice >= 0 && indice < nodos) {
            Informacion[indice] = dato;
        }
    }
    
    public Object getInformacion(int indice) {
        return Informacion[indice];
    }

    public void agregarArista(int i, int j, int peso) {
        if (i >= 0 && j >= 0 && i < nodos && j < nodos) {
            Adyacentes[i][j] = peso;
            
        }
    }
    
    public void agregarArista(int i, int j) {
        agregarArista(i, j, 1);
    }
    
    public int getNodos() {
        return nodos;
    }
    
    public int[][] getMatriz() {
        return Adyacentes;
    }

    public void imprimirMatriz() {
        System.out.println("\n--- Matriz de Adyacencia ---");
        System.out.print("  ");
        for (int k = 0; k < nodos; k++) {
            System.out.print(Informacion[k] != null ? Informacion[k] + " " : k + " ");
        }
        System.out.println();
        for (int i = 0; i < nodos; i++) {
            System.out.print(Informacion[i] != null ? Informacion[i] + " " : i + " ");
            for (int j = 0; j < nodos; j++) {
                System.out.print(Adyacentes[i][j] + " ");
            }
            System.out.println();
        }
    }
}