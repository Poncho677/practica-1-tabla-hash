public class Pruebas {
    public static void main(String[] args) {
        System.out.println("== TablaHash (encadenamiento) ==");
        encadenamiento();

        seccion("== TablaHashSondeoLineal ==");
        sondeoLineal();
    }

    private static void seccion(String titulo) {
        System.out.println();
        System.out.println(titulo);
    }

    private static void encadenamiento() {
        TablaHash t = new TablaHash();

        seccion("Prueba 1");
        t.buscar(10);

        seccion("Prueba 2");
        t.insertar(18, "Ana");
        t.insertar(10, "Luis");
        t.insertar(23, "Elena");
        t.buscar(18);
        t.buscar(10);
        t.buscar(23);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Prueba 3");
        t.insertar(24, "Maria");
        t.insertar(31, "Carlos");
        t.buscar(10);
        t.buscar(24);
        t.buscar(31);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Prueba 4");
        t.eliminar(24);
        t.buscar(24);
        t.buscar(10);
        t.buscar(31);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Prueba 5");
        t.eliminar(999);

        seccion("Prueba 6");
        t.insertar(18, "Ana");
        t.insertar(18, "Ana Maria");
        t.buscar(18);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Ejecucion final");
        TablaHash t2 = new TablaHash();
        t2.insertar(18, "Ana");
        t2.insertar(10, "Luis");
        t2.insertar(24, "Maria");
        t2.insertar(31, "Carlos");
        System.out.print(t2);
        t2.buscar(24);
        t2.buscar(99);
        t2.eliminar(24);
        System.out.print(t2);
        t2.buscar(24);
        t2.buscar(31);
    }

    private static void sondeoLineal() {
        TablaHashSondeoLineal t = new TablaHashSondeoLineal();

        seccion("Prueba 1");
        t.buscar(10);

        seccion("Prueba 2");
        t.insertar(18, "Ana");
        t.insertar(10, "Luis");
        t.insertar(23, "Elena");
        t.buscar(18);
        t.buscar(10);
        t.buscar(23);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Prueba 3");
        t.insertar(24, "Maria");
        t.insertar(31, "Carlos");
        t.buscar(10);
        t.buscar(24);
        t.buscar(31);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Prueba 4");
        t.eliminar(24);
        t.buscar(24);
        t.buscar(10);
        t.buscar(31);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Prueba 5");
        t.eliminar(999);

        seccion("Prueba 6");
        t.insertar(18, "Ana");
        t.insertar(18, "Ana Maria");
        t.buscar(18);
        System.out.println("Factor de carga: " + t.factorCarga());

        seccion("Ejecucion final");
        TablaHashSondeoLineal t2 = new TablaHashSondeoLineal();
        t2.insertar(18, "Ana");
        t2.insertar(10, "Luis");
        t2.insertar(24, "Maria");
        t2.insertar(31, "Carlos");
        System.out.print(t2);
        t2.buscar(24);
        t2.buscar(99);
        t2.eliminar(24);
        System.out.print(t2);
        t2.buscar(24);
        t2.buscar(31);
    }
}
