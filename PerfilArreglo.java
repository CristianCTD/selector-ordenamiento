public class PerfilArreglo {
    private final int tamano;
    private final int min;
    private final int max;
    private final int rango;
    private final boolean completamenteOrdenado;
    private final boolean invertido;
    private final boolean casiOrdenado;
    private final boolean rangoPequeno;
    private final double porcentajeInversiones;

    public PerfilArreglo(int tamano, int min, int max, int rango,
                         boolean completamenteOrdenado, boolean invertido,
                         boolean casiOrdenado, boolean rangoPequeno,
                         double porcentajeInversiones) {
        this.tamano = tamano;
        this.min = min;
        this.max = max;
        this.rango = rango;
        this.completamenteOrdenado = completamenteOrdenado;
        this.invertido = invertido;
        this.casiOrdenado = casiOrdenado;
        this.rangoPequeno = rangoPequeno;
        this.porcentajeInversiones = porcentajeInversiones;
    }

    public int getTamano() { return tamano; }
    public int getMin() { return min; }
    public int getMax() { return max; }
    public int getRango() { return rango; }
    public boolean isCompletamenteOrdenado() { return completamenteOrdenado; }
    public boolean isInvertido() { return invertido; }
    public boolean isCasiOrdenado() { return casiOrdenado; }
    public boolean isRangoPequeno() { return rangoPequeno; }
    public double getPorcentajeInversiones() { return porcentajeInversiones; }

    @Override
    public String toString() {
        return String.format(
            "Tamano: %d | Rango: [%d a %d] (Amplitud: %d) | Ordenado: %s | Invertido: %s | Casi ordenado: %s | Rango Pequeno: %s (Inversiones: %.2f%%)",
            tamano, min, max, rango,
            completamenteOrdenado ? "SI" : "NO",
            invertido ? "SI" : "NO",
            casiOrdenado ? "SI" : "NO",
            rangoPequeno ? "SI" : "NO",
            porcentajeInversiones
        );
    }
}
