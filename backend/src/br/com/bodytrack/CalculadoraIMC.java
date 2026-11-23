
package br.com.bodytrack;

public class CalculadoraIMC {

    public static double calcular(double peso, double altura) {

        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException(
                "Peso e altura devem ser maiores que zero."
            );
        }

        return peso / (altura * altura);
    }
}
