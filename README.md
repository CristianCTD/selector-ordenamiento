# Selector Inteligente de Algoritmos de Ordenamiento

**Asignatura:** Estructuras de Datos  
**Unidad:** Unidad 3 - Taller Práctico: Algoritmos de Ordenamiento  
**Programa:** Ingeniería de Software  
**IES:** Universidad de Cartagena  
**Tutor:** John Carlos Arrieta Arrieta  
**Estudiante:** Cristian Torres  
**Ejercicio Asignado:** N.º 30 - Selector Inteligente de Algoritmo

---

## 1. Descripción del Ejercicio
El **Selector Inteligente de Algoritmo** es un sistema heurístico que analiza en tiempo de ejecución las características estructurales de una colección de datos antes de decidir la estrategia de ordenamiento más eficiente:

1. **Propiedades analizadas en $O(n)$:**
   - Tamaño del arreglo ($n$).
   - Amplitud y cotas del rango numérico ($max - min$).
   - Porcentaje de inversiones (evaluación de datos casi ordenados).
   - Orientación monótona (completamente ordenado o invertido).
2. **Algoritmos implementados manualmente (sin `Arrays.sort`):**
   - **`Insertion Sort`:** Seleccionado para arreglos pequeños ($n \le 15$) o casi ordenados (tiempo $O(n)$).
   - **`Bubble Sort Optimizado`:** Detención temprana mediante bandera de intercambio.
   - **`Quick Sort`:** Seleccionado para colecciones grandes desordenadas (particionamiento central in-place $O(n \log n)$).
   - **`Merge Sort`:** Seleccionado para arreglos invertidos o que requieren estabilidad y garantía de tiempo $O(n \log n)$.
   - **`Counting Sort`:** Seleccionado para rangos numéricos pequeños no negativos (complejidad lineal $O(n + k)$ sin comparaciones de pares).
3. **Métricas de instrumentación:**
   - Comparaciones exactas.
   - Movimientos / intercambios de memoria.
   - Tiempo de ejecución en nanosegundos.
   - Verificación de post-condición `isSortedAsc()`.

---

## 2. Compilación y Ejecución

### Compilación:
```bash
javac *.java
```

### Ejecución:
```bash
java Main
```
