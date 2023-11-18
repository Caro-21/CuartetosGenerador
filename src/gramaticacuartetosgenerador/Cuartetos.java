package gramaticacuartetosgenerador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Cuartetos {

    String[][] cuarteto = new String[20][4];
    int k = 0;
    int temporal = 1;
    int asigna = 0;

    private void leerPosfija() throws FileNotFoundException {
        File archivo = new File("src/codigointermedio/Posfija.txt");
        Scanner lexico = new Scanner(new FileReader(archivo));
        while (lexico.hasNext()) {
            buscar(lexico.next());
        }
    }

    private void mostrarCuarteto() {
        System.out.println("Operador\tOperando1\tOperando2\tResultado");

        for (int i = 0; i < k; i++) {
            String operador = cuarteto[i][0];
            String operando1 = cuarteto[i][1].equals("") ? "_" : cuarteto[i][1];
            String operando2 = cuarteto[i][2].equals("") ? "_" : cuarteto[i][2];
            String resultado = cuarteto[i][3].equals("") ? "_" : cuarteto[i][3];

            if (operador.equals("read")) {
                operando1 = "_";
                operando2 = "_";
            }

            System.out.printf("%-10s%-12s%-12s%-12s%n", operador, operando1, operando2, resultado);
        }
    }

    private void buscar(String lexema) {
        if ("+".equals(lexema) || "-".equals(lexema) || "*".equals(lexema)) {
            cuarteto[k][0] = "Int";
            cuarteto[k][1] = "_";
            cuarteto[k][2] = "_";
            cuarteto[k][3] = "_";
            k++;
        } else if ("=".equals(lexema)) {
            asigna = 1;
        } else if ("read".equals(lexema) || "write".equals(lexema)) {
            cuarteto[k][0] = lexema.equals("read") ? "Int" : "write";
            cuarteto[k][1] = "_";
            cuarteto[k][2] = "_";
            cuarteto[k][3] = lexema.equals("read") ? "Numero" : "Resultado";
            k++;
        } else {
            if (k < cuarteto.length) {
                if (asigna == 0) {
                    cuarteto[k][0] = "Int";
                    cuarteto[k][1] = lexema.equals("Numero") ? "Numero" : "Cuenta";
                    cuarteto[k][2] = "_";
                    cuarteto[k][3] = "_";
                } else {
                    cuarteto[k][0] = "Int";
                    cuarteto[k][1] = "_";
                    cuarteto[k][2] = lexema;
                    cuarteto[k][3] = asigna == 1 ? cuarteto[k - 1][3] : "temp" + temporal++;
                    asigna = 0;
                    k++;
                }
            } else {
                System.out.println("No hay espacio en el arreglo de cuartetos para almacenar");
            }
        }
    }

    private void inicializarTablas() {
        for (String[] fila : cuarteto) {
            for (int j = 0; j < fila.length; j++) {
                fila[j] = "";
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Cuartetos cuarteto = new Cuartetos();
        cuarteto.inicializarTablas();
        cuarteto.leerPosfija();
        cuarteto.mostrarCuarteto();
    }
}
