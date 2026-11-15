import java.util.Arrays;

public class SelectorOrdenamiento {

    public static PerfilArreglo analizarArreglo(int[] a) {
        if (a == null || a.length == 0) {
            return new PerfilArreglo(0, 0, 0, 0, true, false, false, false, 0.0);
        }

        int n = a.length;
        int min = a[0];
        int max = a[0];
        int desordenes = 0;
        boolean creciente = true;
        boolean decreciente = true;

        for (int i = 0; i < n; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
            if (i > 0) {
                if (a[i - 1] > a[i]) {
                    creciente = false;
                    desordenes++;
                }
                if (a[i - 1] < a[i]) {
                    decreciente = false;
                }
            }
        }

        int rango = max - min;
        double pctDesorden = (n > 1) ? ((double) desordenes / (n - 1)) * 100.0 : 0.0;
        boolean completamenteOrdenado = creciente;
        boolean invertido = decreciente && (n > 1);
        boolean casiOrdenado = !creciente && (pctDesorden <= 15.0);
        boolean rangoPequeno = (min >= 0 && rango <= 300 && n >= 10);

        return new PerfilArreglo(n, min, max, rango, completamenteOrdenado,
                                 invertido, casiOrdenado, rangoPequeno, pctDesorden);
    }

    public static ResultadoOrdenamiento ordenarInteligente(int[] original) {
        if (original == null || original.length == 0) {
            return new ResultadoOrdenamiento("Ninguno", "Arreglo vacio", new int[0], 0, 0, 0, true);
        }

        int[] a = original.clone();
        PerfilArreglo perfil = analizarArreglo(a);

        if (perfil.isCompletamenteOrdenado()) {
            return new ResultadoOrdenamiento(
                "Ninguno (Ya ordenado)",
                "El arreglo ya se encuentra completamente ordenado de menor a mayor. Costo de ordenamiento evitado: 0 operaciones.",
                a, 0, 0, 0, true
            );
        }

        if (perfil.isRangoPequeno()) {
            return ejecutarCountingSort(a, perfil.getMax(),
                "Valores enteros no negativos acotados en un rango pequeno (amplitud " + perfil.getRango() +
                "). Counting Sort opera en tiempo lineal O(n + k) superando la barrera comparativa O(n log n).");
        }

        if (perfil.isCasiOrdenado()) {
            return ejecutarInsertionSort(a,
                "El arreglo esta casi ordenado (solo " + String.format("%.1f", perfil.getPorcentajeInversiones()) +
                "% de inversiones). Insertion Sort aprovecha el orden parcial y opera en tiempo casi lineal O(n).");
        }

        if (perfil.isInvertido()) {
            return ejecutarMergeSort(a,
                "El arreglo se encuentra estrictamente invertido (peor caso para QuickSort con pivote extremo y BubbleSort). " +
                "Merge Sort garantiza complejidad O(n log n) constante sin riesgo de degradacion.");
        }

        if (perfil.getTamano() <= 15) {
            return ejecutarInsertionSort(a,
                "Arreglo de tamano muy pequeno (n = " + perfil.getTamano() +
                "). Insertion Sort presenta el menor overhead computacional y aprovecha la cache de CPU.");
        }

        return ejecutarQuickSort(a,
            "Arreglo general desordenado de tamano considerable (n = " + perfil.getTamano() +
            "). Quick Sort con pivote medio ofrece el mejor rendimiento practico in-place con complejidad promedio O(n log n).");
    }

    public static ResultadoOrdenamiento ejecutarInsertionSort(int[] a, String justificacion) {
        long comp = 0;
        long mov = 0;
        long tInicio = System.nanoTime();

        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0) {
                comp++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    mov++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
            mov++;
        }

        long tFin = System.nanoTime();
        return new ResultadoOrdenamiento("Insertion Sort", justificacion, a, comp, mov, (tFin - tInicio), isSortedAsc(a));
    }

    public static ResultadoOrdenamiento ejecutarBubbleSortOptimizado(int[] a, String justificacion) {
        long comp = 0;
        long mov = 0;
        long tInicio = System.nanoTime();

        boolean swapped;
        for (int n = a.length; n > 1; n--) {
            swapped = false;
            for (int i = 1; i < n; i++) {
                comp++;
                if (a[i - 1] > a[i]) {
                    int tmp = a[i];
                    a[i] = a[i - 1];
                    a[i - 1] = tmp;
                    mov++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        long tFin = System.nanoTime();
        return new ResultadoOrdenamiento("Bubble Sort Optimizado", justificacion, a, comp, mov, (tFin - tInicio), isSortedAsc(a));
    }

    public static ResultadoOrdenamiento ejecutarQuickSort(int[] a, String justificacion) {
        long[] metricas = new long[3];
        long tInicio = System.nanoTime();

        quickSortRec(a, 0, a.length - 1, metricas);

        long tFin = System.nanoTime();
        return new ResultadoOrdenamiento("Quick Sort", justificacion, a, metricas[0], metricas[1], (tFin - tInicio), isSortedAsc(a));
    }

    private static void quickSortRec(int[] a, int lo, int hi, long[] metricas) {
        metricas[2]++;
        int i = lo, j = hi;
        int pivot = a[lo + (hi - lo) / 2];

        while (i <= j) {
            while (a[i] < pivot) {
                metricas[0]++;
                i++;
            }
            metricas[0]++;

            while (a[j] > pivot) {
                metricas[0]++;
                j--;
            }
            metricas[0]++;

            if (i <= j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                metricas[1]++;
                i++;
                j--;
            }
        }

        if (lo < j) quickSortRec(a, lo, j, metricas);
        if (i < hi) quickSortRec(a, i, hi, metricas);
    }

    public static ResultadoOrdenamiento ejecutarMergeSort(int[] a, String justificacion) {
        long[] metricas = new long[2];
        long tInicio = System.nanoTime();

        int[] buffer = a.clone();
        mergeSortRec(a, buffer, 0, a.length, metricas);

        long tFin = System.nanoTime();
        return new ResultadoOrdenamiento("Merge Sort", justificacion, a, metricas[0], metricas[1], (tFin - tInicio), isSortedAsc(a));
    }

    private static void mergeSortRec(int[] a, int[] buffer, int lo, int hi, long[] metricas) {
        if (hi - lo < 2) return;
        int mid = (lo + hi) / 2;

        mergeSortRec(a, buffer, lo, mid, metricas);
        mergeSortRec(a, buffer, mid, hi, metricas);

        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
            metricas[0]++;
            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
            }
            metricas[1]++;
        }
        while (i < mid) {
            buffer[k++] = a[i++];
            metricas[1]++;
        }
        while (j < hi) {
            buffer[k++] = a[j++];
            metricas[1]++;
        }

        System.arraycopy(buffer, lo, a, lo, hi - lo);
        metricas[1] += (hi - lo);
    }

    public static ResultadoOrdenamiento ejecutarCountingSort(int[] a, int maxValue, String justificacion) {
        long comp = 0;
        long mov = 0;
        long tInicio = System.nanoTime();

        int[] count = new int[maxValue + 1];
        for (int v : a) {
            count[v]++;
            mov++;
        }

        int index = 0;
        for (int v = 0; v <= maxValue; v++) {
            int c = count[v];
            while (c-- > 0) {
                a[index++] = v;
                mov++;
            }
        }

        long tFin = System.nanoTime();
        return new ResultadoOrdenamiento("Counting Sort", justificacion, a, comp, mov, (tFin - tInicio), isSortedAsc(a));
    }

    public static boolean isSortedAsc(int[] a) {
        if (a == null) return true;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) return false;
        }
        return true;
    }
}
