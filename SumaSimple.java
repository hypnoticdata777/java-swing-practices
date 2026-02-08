import java.util.Scanner;

public class SumaSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el primer número: ");
        int num1 = sc.nextInt();

        System.out.print("Ingresa el segundo número: ");
        int num2 = sc.nextInt();

        System.out.print("Ingresa el número objetivo: ");
        int objetivo = sc.nextInt();

        int suma = num1 + num2;

        if (suma == objetivo) {
            System.out.println("\n✔ ¡Correcto! " + num1 + " + " + num2 + " = " + suma);
        } else {
            System.out.println("\n✘ Incorrecto. " + num1 + " + " + num2 + " = " + suma);
        }

        sc.close();
    }
}
