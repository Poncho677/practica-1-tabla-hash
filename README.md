# Practica-1 :smile:
# Equipo: 2 y un infiltrado :shushing_face:
## Integrantes:
* Cruz Escobar Aarón
* Góngora Barroso Alfonso
* Quirino Roman Emmanuel

## ¿Por qué tener una colisión no significa que la tabla hash esté implementada incorrectamente?

## 1. Lenguaje utilizado

Java.

## 2. Instrucciones para ejecutar el programa

No se requieren dependencias externas, solo el JDK.

```bash
javac *.java
java Pruebas
```

Esto compila las tres clases (`TablaHash`, `TablaHashSondeoLineal` y `Pruebas`) y ejecuta directamente las pruebas descritas abajo.

Si se quiere usar cualquiera de las tablas por separado:

```java
TablaHash tabla = new TablaHash();
tabla.insertar(1, "uno");
tabla.buscar(1);
tabla.eliminar(1);
```

```java
TablaHashSondeoLineal tabla = new TablaHashSondeoLineal();
tabla.insertar(2, "dos");
tabla.buscar(2); 
```

## 3. Explicación de cómo ejecutar los casos de prueba

`Pruebas.java` no usa ningún framework de pruebas unitarias. Al correr `java Pruebas`, el `main` ejecuta, una tras otra y para cada implementación (`TablaHash` y `TablaHashSondeoLineal`), las siguientes pruebas obligatorias, imprimiendo en consola el resultado real de cada operación para compararlo a simple vista:

1. **Prueba 1 — Tabla vacía**: `buscar(10)` → `NOT_FOUND`.
2. **Prueba 2 — Inserción básica**: insertar `18, 10, 23` y buscar cada uno.
3. **Prueba 3 — Colisiones**: insertar `24` y `31`, que colisionan con
   `10` (`hash(10) = hash(24) = hash(31) = 3`), y verificar que los tres
   siguen siendo accesibles.
4. **Prueba 4 — Eliminación con colisión**: eliminar `24` y confirmar
   que `10` y `31` siguen intactos.
5. **Prueba 5 — Llave inexistente**: `eliminar(999)` no debe lanzar
   errores ni modificar la tabla.
6. **Prueba 6 — Actualización de llave existente**: insertar `18` dos
   veces con valores distintos y verificar que solo queda el último
   (`"Ana Maria"`).

Después se imprime el factor de carga de esa misma tabla, y por último se repite la secuencia de ejecución final sugerida (insertar, imprimir la tabla completa con `toString()`, buscar, eliminar y volver a imprimir), para verificar el comportamiento de extremo a extremo.

## 4. Explicación de la función hash

Calcula el módulo 7 de la llave entregada: `hash(k) = |k| % m`, con `m = 7`. Se usa `Math.abs` para que las llaves negativas también produzcan un índice válido dentro del arreglo (`0` a `6`).

## 5. Explicación del manejo de colisiones

Cada implementación las resuelve de forma distinta:

- **`TablaHash` (encadenamiento)**: cada cubeta del arreglo es una lista (`ArrayList<Nodo>`). Cuando una llave nueva produce el mismo hash que una ya existente, simplemente se agrega a la lista de esa cubeta, en vez de sustituir lo que ya había.
- **`TablaHashSondeoLineal` (sondeo lineal)**: cada posición del arreglo guarda a lo más una llave. Si la posición calculada ya está ocupada por otra llave, se prueba la siguiente posición `(indice + 1) % m`, y así sucesivamente hasta encontrar un espacio libre.

## 6. Explicación de qué ocurre cuando dos llaves producen el mismo hash

En `TablaHash`, ambas llaves terminan viviendo en la misma cubeta, una al lado de la otra dentro de la misma lista; ninguna sobrescribe a la otra mientras sean llaves distintas. En `TablaHashSondeoLineal`, la segunda llave no puede quedarse en esa posición porque ya está ocupada, así que se desplaza automáticamente a la siguiente posición libre siguiendo el sondeo lineal.

## 7. Explicación de qué ocurre cuando varias llaves caen en la misma cubeta

Es la misma idea, extendida a más de dos llaves. En el ejemplo de las pruebas, `10`, `24` y `31` comparten `hash = 3`:

- En `TablaHash`, las tres coexisten en la lista de la cubeta `3`: `[(10, Luis), (24, Maria), (31, Carlos)]`. Buscar cualquiera de ellas recorre esa lista hasta encontrar la llave correcta; eliminar una (por ejemplo `24`) solo quita ese nodo de la lista, dejando `10` y `31` intactos.
- En `TablaHashSondeoLineal`, solo `10` queda en la posición `3`; `24` se desplaza a la posición `4` (si está libre) o a la siguiente disponible, y `31` sigue sondeando hasta hallar la suya. Cada una termina en una posición física distinta del arreglo, aunque las tres comparten el mismo hash de origen. Al eliminar una, su posición se marca como `DELETED` (no se vacía), precisamente para no romper el camino de sondeo de las demás.

## 8. Factor de carga final obtenido durante sus pruebas

Se imprime en cada paso relevante para mostrar cómo cambia (`n / m`, con `m = 7`), y termina en **4/7 ≈ 0.5714** para ambas implementaciones:

| Momento                                   | Factor de carga |
|--------------------------------------------|:---------------:|
| Tras Prueba 2 (insertar 18, 10, 23)         | 3/7 ≈ 0.4286     |
| Tras Prueba 3 (insertar 24, 31 en colisión) | 5/7 ≈ 0.7143     |
| Tras Prueba 4 (eliminar 24)                 | 4/7 ≈ 0.5714     |
| Tras Prueba 6 (actualizar 18, no crece)     | 4/7 ≈ 0.5714     |

Sube con cada inserción de una llave nueva, baja al eliminar, y se mantiene igual al actualizar el valor de una llave ya existente, ya que eso no agrega un elemento nuevo a la tabla.
