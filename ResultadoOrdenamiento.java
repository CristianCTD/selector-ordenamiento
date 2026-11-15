import java.util.Arrays;

public class ResultadoOrdenamiento {
    private final String algoritmo;
    private final String justificacionRegla;
    private final int[] arregloOrdenado;
    private final long comparaciones;
    private final long movimientosOIntercambios;
    private final long tiempoNano;
    private final boolean verificadoOrdenado;

    public ResultadoOrdenamiento(String algoritmo, String justificacionRegla, int[] arregloOrdenado,
                                 long comparaciones, long movimientosOIntercambios,
                                 long tiempoNano, boolean verificadoOrdenado) {
        this.algoritmo = algoritmo;
        this.justificacionRegla = justificacionRegla;
        this.arregloOrdenado = arregloOrdenado;
        this.comparaciones = comparaciones;
        this.movimientosOIntercambios = movimientosOIntercambios;
        this.tiempoNano = tiempoNano;
        this.verificadoOrdenado = verificadoOrdenado;
    }

    public String getAlgoritmo() { return algoritmo; }
    public String getJustificacionRegla() { return justificacionRegla; }
    public int[] getArregloOrdenado() { return arregloOrdenado; }
    public long getComparaciones() { return comparaciones; }
    public long getMovimientosOIntercambios() { return movimientosOIntercambios; }
    public long getTiempoNano() { return tiempoNano; }
    public boolean isVerificadoOrdenado() { return verificadoOrdenado; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================================================\n");
        sb.append(" REPORTE DE SELECCION INTELIGENTE DE ORDENAMIENTO\n");
        sb.append("=======================================================================\n");
        sb.append(" Algoritmo Seleccionado : ").append(algoritmo).append("\n");
        sb.append(" Justificacion Tecnica  : ").append(justificacionRegla).append("\n");
        sb.append(" Comparaciones          : ").append(String.format("%,d", comparaciones)).append("\n");
        sb.append(" Movimientos / Swaps    : ").append(String.format("%,d", movimientosOIntercambios)).append("\n");
        sb.append(" Tiempo de ejecucion    : ").append(String.format("%,d", tiempoNano)).append(" ns (")
          .append(String.format("%.4f", tiempoNano / 1_000_000.0)).append(" ms)\n");
        sb.append(" Verificacion Correcta  : ").append(verificadoOrdenado ? "SI (Ordenado Ascendente)" : "ERROR NO ORDENADO").append("\n");
        if (arregloOrdenado.length <= 40) {
            sb.append(" Arreglo Ordenado       : ").append(Arrays.toString(arregloOrdenado)).append("\n");
        } else {
            sb.append(" Arreglo Ordenado       : [Primeros 10: ")
              .append(Arrays.toString(Arrays.copyOfRange(arregloOrdenado, 0, 10)))
              .append(" ... Ultimos 5: ")
              .append(Arrays.toString(Arrays.copyOfRange(arregloOrdenado, arregloOrdenado.length - 5, arregloOrdenado.length)))
              .append("]\n");
        }
        sb.append("=======================================================================");
        return sb.toString();
    }
}
