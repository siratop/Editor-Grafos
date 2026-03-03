package estructuras;

import java.util.LinkedList;
import java.util.Queue;

public class Algoritmos {

    // ---  Busqueda en Profundidad (DFS) ---
    public static String recorridoDFS(Grafo g, int nodoInicial) {
        StringBuilder sb = new StringBuilder();
        sb.append("Recorrido DFS: ");
        boolean[] visitados = new boolean[g.getNodos()];
        dfsRecursivo(g, nodoInicial, visitados, sb);
        sb.append("\n"); 
        return sb.toString();
    }

    private static void dfsRecursivo(Grafo g, int nodoActual, boolean[] visitados, StringBuilder sb) {
        visitados[nodoActual] = true;
        sb.append(g.getInformacion(nodoActual)).append(" -> ");

        int[][] matriz = g.getMatriz();
        for (int i = 0; i < g.getNodos(); i++) {
            if (matriz[nodoActual][i] > 0 && !visitados[i]) {
                dfsRecursivo(g, i, visitados, sb);
            }
        }
    }

    // ---  Busqueda en Amplitud (BFS) ---
    public static String recorridoBFS(Grafo g, int nodoInicial) {
        StringBuilder sb = new StringBuilder();
        sb.append("Recorrido BFS: ");
        boolean[] visitados = new boolean[g.getNodos()];
        Queue<Integer> cola = new LinkedList<>();

        visitados[nodoInicial] = true;
        cola.add(nodoInicial);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            sb.append(g.getInformacion(actual)).append(" -> ");

            int[][] matriz = g.getMatriz();
            for (int i = 0; i < g.getNodos(); i++) {
                if (matriz[actual][i] > 0 && !visitados[i]) {
                    visitados[i] = true;
                    cola.add(i);
                }
            }
        }
        sb.append("Fin\n");
        return sb.toString();
    }
    
    // ---  Algoritmo de Dijkstra  ---
    public static String dijkstra(Grafo g, int nodoOrigen) {
        StringBuilder sb = new StringBuilder();
        int n = g.getNodos();
        int[][] matriz = g.getMatriz();
        int[] distancias = new int[n];
        boolean[] visitados = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitados[i] = false;
        }
        distancias[nodoOrigen] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            int minVal = Integer.MAX_VALUE;
            for (int k = 0; k < n; k++) {
                if (!visitados[k] && distancias[k] < minVal) {
                    minVal = distancias[k];
                    u = k;
                }
            }

            if (u == -1) break; 
            visitados[u] = true;

            for (int v = 0; v < n; v++) {
                if (matriz[u][v] > 0 && !visitados[v]) {
                    if (distancias[u] != Integer.MAX_VALUE && 
                        distancias[u] + matriz[u][v] < distancias[v]) {
                        distancias[v] = distancias[u] + matriz[u][v];
                    }
                }
            }
        }

        sb.append("\n--- Resultados Dijkstra desde ").append(g.getInformacion(nodoOrigen)).append(" ---\n");
        for (int i = 0; i < n; i++) {
            sb.append("Hasta ").append(g.getInformacion(i)).append(": ");
            if (distancias[i] != Integer.MAX_VALUE) {
                sb.append(distancias[i]);
            } else {
                sb.append("INFINITO");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // ---  Algoritmo de Prim  ---
    public static String prim(Grafo g, int nodoOrigen) {
        StringBuilder sb = new StringBuilder();
        int n = g.getNodos();
        int[][] matriz = g.getMatriz();
        
        int[] key = new int[n];      
        int[] padre = new int[n];    
        boolean[] enMST = new boolean[n]; 
        
       
        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            enMST[i] = false;
        }
        
        
        key[nodoOrigen] = 0;
        padre[nodoOrigen] = -1;
        
        for (int count = 0; count < n - 1; count++) {
            
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int v = 0; v < n; v++) {
                if (!enMST[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }
            
            if (u == -1) break; 
            enMST[u] = true;
            
           
            for (int v = 0; v < n; v++) {
        
                if (matriz[u][v] > 0 && !enMST[v] && matriz[u][v] < key[v]) {
                    padre[v] = u;
                    key[v] = matriz[u][v];
                }
            }
        }
        
       
        sb.append("\n--- Arbol Mínimo de Expansión (Prim) ---\n");
        int costoTotal = 0;
        for (int i = 0; i < n; i++) {
            if (padre[i] != -1) {
                sb.append("Arista: ").append(g.getInformacion(padre[i]))
                  .append(" -> ").append(g.getInformacion(i))
                  .append("   [Costo: ").append(matriz[padre[i]][i]).append("]\n");
                costoTotal += matriz[padre[i]][i];
            }
        }
        sb.append("\n>>> Costo Total del Arbol: ").append(costoTotal).append("\n");
        
        return sb.toString();
    }
}