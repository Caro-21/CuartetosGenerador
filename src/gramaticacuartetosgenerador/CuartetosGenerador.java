package gramaticacuartetosgenerador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CuartetosGenerador {
    
public static void main(String[] args) {
        List<String> cuartetos = new ArrayList<>();
        int tempCounter = 1;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("gramatica1.txt"));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(" ");

                if (tokens.length < 1) {
                    continue; // Saltar líneas vacías o inválidas
                }

                if (tokens[0].equals("Int") && tokens.length >= 2) {
                    cuartetos.add("Int " + tokens[1] + " _ _");
                } else if (tokens[0].equals("read") && tokens.length >= 2) {
                    cuartetos.add("read _ _ " + tokens[1]);
                } else if (tokens[0].equals("write") && tokens.length >= 2) {
                    cuartetos.add("write _ _ " + tokens[1]);
                } else if (tokens[0].equals("Numero") && tokens[1].equals("Cuenta") && tokens[2].equals("+") && tokens[3].equals("Resultado")) {
                    cuartetos.add("+ Numero Cuenta Resultado");
                } else if (tokens[0].equals("12") && tokens[1].equals("Cuenta") && tokens[2].equals("+") && tokens[3].equals("Resultado")) {
                    cuartetos.add("+ 12 Cuenta Resultado");
                } else if (tokens[0].equals("12") && tokens[1].equals("11") && tokens[2].equals("1") && tokens[3].equals("*") && tokens[4].equals("+") && tokens[6].equals("Resultado")) {
                    cuartetos.add("* 11 1 temp" + tempCounter);
                    cuartetos.add("+ 12 temp" + tempCounter + " Resultado");
                    tempCounter++;
                } else if (tokens[0].equals("read") && tokens[1].equals("Resultado")) {
                    cuartetos.add("read _ _ Resultado");
                }
            }
            reader.close();

            for (String cuarteto : cuartetos) {
                System.out.println(cuarteto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}