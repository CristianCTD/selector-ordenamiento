import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private final Scanner scanner;

    public Main() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion (0-7): ");
            System.out.println();
            switch (opcion) {
                case 1:
                    probarCasoCasiOrdenado();
                    break;
                case 2:
                    probarCasoRangoPequeno();
                    break;
                case 3:
                    probarCasoInvertido();
                    break;
                case 4:
                    probarCasoGrandeDesordenado();
                    break;
                case 5:
                    probarCasoPequeno();
                    break;
                case 6:
                    probarCasoPersonalizado();
                    break;
                case 7:
                    comparativaTodosLosAlgoritmos();
                    break;
                case 0:
                    System.out.println("=======================================================================");
                    System.out.println(" Gracias por utilizar el Selector Inteligente de Algoritmos.");
                    System.out.println(" Universidad de Cartagena - Ingenieria de Software - Unidad 3.");
                    System.out.println("=======================================================================");
                    break;
                default:
                    System.out.println("[Error] Opcion invalida. Ingrese un valor entre 0 y 7.");
            }
            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n=======================================================================");
        System.out.println(" SELECTOR INTELIGENTE DE ALGORITMOS DE ORDENAMIENTO");
        System.out.println(" Ejercicio N. 30 | Estudiante: Cristian Torres | U. de Cartagena");
        System.out.println("=======================================================================");
        System.out.println(" 1. Probar Arreglo Casi Ordenado (Auto-seleccion: Insertion Sort)");
        System.out.println(" 2. Probar Rango Numerico Pequeno (Auto-seleccion: Counting Sort O(n+k))");
        System.out.println(" 3. Probar Arreglo Invertido / Descendente (Auto-seleccion: Merge Sort)");
        System.out.println(" 4. Probar Arreglo Grande Desordenado (Auto-seleccion: Quick Sort)");
        System.out.println(" 5. Probar Arreglo Muy Pequeno (Auto-seleccion: Insertion Sort)");
        System.out.println(" 6. Ingresar Arreglo Personalizado (Prueba libre)");
        System.out.println(" 7. Comparativa: Ejecutar los 5 Algoritmos sobre el mismo conjunto");
        System.out.println(" 0. Salir");
        System.out.println("=======================================================================");
    }

    private void probarCasoCasiOrdenado() {
        System.out.println("--- CASO 1: ARREGLO CASI ORDENADO ---");
        int[] datos = {1, 2, 3, 5, 4, 6, 7, 9, 8, 10, 11, 13, 12, 14, 15, 16};
        System.out.println("Arreglo original: " + Arrays.toString(datos));
        PerfilArreglo perfil = SelectorOrdenamiento.analizarArreglo(datos);
        System.out.println("Analisis del perfil: " + perfil);
        System.out.println();

        ResultadoOrdenamiento res = SelectorOrdenamiento.ordenarInteligente(datos);
        System.out.println(res);
    }

    private void probarCasoRangoPequeno() {
        System.out.println("--- CASO 2: RANGO NUMERICO PEQUENO (EDADES / NOTAS) ---");
        int[] datos = {4, 1, 3, 2, 5, 2, 1, 4, 3, 0, 5, 2, 1, 4, 2, 3, 5, 1, 0, 4};
        System.out.println("Arreglo original: " + Arrays.toString(datos));
        PerfilArreglo perfil = SelectorOrdenamiento.analizarArreglo(datos);
        System.out.println("Analisis del perfil: " + perfil);
        System.out.println();

        ResultadoOrdenamiento res = SelectorOrdenamiento.ordenarInteligente(datos);
        System.out.println(res);
    }

    private void probarCasoInvertido() {
        System.out.println("--- CASO 3: ARREGLO ESTRICTAMENTE INVERTIDO ---");
        int[] datos = {90, 80, 70, 60, 50, 40, 30, 20, 10, 5, 1};
        System.out.println("Arreglo original: " + Arrays.toString(datos));
        PerfilArreglo perfil = SelectorOrdenamiento.analizarArreglo(datos);
        System.out.println("Analisis del perfil: " + perfil);
        System.out.println();

        ResultadoOrdenamiento res = SelectorOrdenamiento.ordenarInteligente(datos);
        System.out.println(res);
    }

    private void probarCasoGrandeDesordenado() {
        System.out.println("--- CASO 4: ARREGLO GRANDE Y DESORDENADO (n = 1.000) ---");
        int n = 1_000;
        int[] datos = new int[n];
        for (int i = 0; i < n; i++) {
            datos[i] = (int) (Math.random() * 50_000);
        }
        System.out.println("Generado arreglo de " + n + " enteros aleatorios.");
        PerfilArreglo perfil = SelectorOrdenamiento.analizarArreglo(datos);
        System.out.println("Analisis del perfil: " + perfil);
        System.out.println();

        ResultadoOrdenamiento res = SelectorOrdenamiento.ordenarInteligente(datos);
        System.out.println(res);
    }

    private void probarCasoPequeno() {
        System.out.println("--- CASO 5: ARREGLO MUY PEQUENO (n = 6) ---");
        int[] datos = {28, 4, 15, 89, 2, 33};
        System.out.println("Arreglo original: " + Arrays.toString(datos));
        PerfilArreglo perfil = SelectorOrdenamiento.analizarArreglo(datos);
        System.out.println("Analisis del perfil: " + perfil);
        System.out.println();

        ResultadoOrdenamiento res = SelectorOrdenamiento.ordenarInteligente(datos);
        System.out.println(res);
    }

    private void probarCasoPersonalizado() {
        System.out.println("--- CASO 6: INGRESO DE ARREGLO PERSONALIZADO ---");
        System.out.print("Ingrese los enteros separados por espacios o comas: ");
        String linea = scanner.nextLine().trim();
        String[] partes = linea.split("[,\\s]+");

        int[] datos = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            datos[i] = Integer.parseInt(partes[i].trim());
        }

        System.out.println("\nAnalizando arreglo...");
        PerfilArreglo perfil = SelectorOrdenamiento.analizarArreglo(datos);
        System.out.println("Perfil detectado: " + perfil);
        System.out.println();

        ResultadoOrdenamiento res = SelectorOrdenamiento.ordenarInteligente(datos);
        System.out.println(res);
    }

    private void comparativaTodosLosAlgoritmos() {
        System.out.println("--- CASO 7: COMPARATIVA DE LOS 5 ALGORITMOS SOBRE COPIAS IDENTICAS ---");
        int n = 500;
        int maxValue = 1_000;
        int[] base = new int[n];
        for (int i = 0; i < n; i++) {
            base[i] = (int) (Math.random() * maxValue);
        }

        System.out.println("Dataset de prueba generado: " + n + " elementos aleatorios en rango [0, " + maxValue + "]");
        System.out.println();

        ResultadoOrdenamiento rBubble = SelectorOrdenamiento.ejecutarBubbleSortOptimizado(base.clone(), "Prueba comparativa");
        ResultadoOrdenamiento rInsertion = SelectorOrdenamiento.ejecutarInsertionSort(base.clone(), "Prueba comparativa");
        ResultadoOrdenamiento rQuick = SelectorOrdenamiento.ejecutarQuickSort(base.clone(), "Prueba comparativa");
        ResultadoOrdenamiento rMerge = SelectorOrdenamiento.ejecutarMergeSort(base.clone(), "Prueba comparativa");
        ResultadoOrdenamiento rCounting = SelectorOrdenamiento.ejecutarCountingSort(base.clone(), maxValue, "Prueba comparativa");

        System.out.printf("%-24s | %-15s | %-18s | %-15s | %-10s%n",
                          "Algoritmo", "Comparaciones", "Movimientos/Swaps", "Tiempo (ms)", "Correcto");
        System.out.println("---------------------------------------------------------------------------------------------");
        imprimirFilaMetricas(rBubble);
        imprimirFilaMetricas(rInsertion);
        imprimirFilaMetricas(rQuick);
        imprimirFilaMetricas(rMerge);
        imprimirFilaMetricas(rCounting);
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Conclusion observada:");
        System.out.println("- Bubble e Insertion tienen costo cuadratico O(n^2) en datos desordenados.");
        System.out.println("- QuickSort y MergeSort reducen drasticamente comparaciones a O(n log n).");
        System.out.println("- CountingSort no realiza comparaciones entre pares (O(0) comp) y procesa en tiempo lineal O(n+k).");
    }

    private void imprimirFilaMetricas(ResultadoOrdenamiento r) {
        System.out.printf("%-24s | %-15s | %-18s | %-15.4f | %-10s%n",
                          r.getAlgoritmo(),
                          String.format("%,d", r.getComparaciones()),
                          String.format("%,d", r.getMovimientosOIntercambios()),
                          r.getTiempoNano() / 1_000_000.0,
                          r.isVerificadoOrdenado() ? "OK" : "FALLO");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                if (!mensaje.isEmpty()) {
                    System.out.print(mensaje);
                }
                String linea = scanner.nextLine().trim();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("[Error] Entrada invalida. Ingrese un numero entero.");
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.iniciar();
    }
}
