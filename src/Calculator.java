import java.util.Scanner;

public class Calculator {
    private char option;
    private String operation;
    private Scanner intro = new Scanner(System.in);

    public static void main(String[] args) {
        Calculator calculadora = new Calculator();
        calculadora.initiateCalculator();
    }

    private void initiateCalculator() {
        // Monta la calculadora, mostrando el menu y pidiendo una opcion (notar que la opcion devuelve
        // en formato char). Repite este proceso mientras la opcion no este entr 1 y 6 (respresentados
        // por 49 y 54 en el codigo ASCII) o si la opcion es igual a 'Y'(78 en ASCII).
        // Si la opcion es 'N' (89 en ASCII) sale del metodo.
        // Si se obtiene una opcion valida se llama al metodo operateWithOption() para haccer la
        // operacion correspondiente y se vuelve a armar la calculadora llamandose initiateCalculator()
        // a si mismo.

        do {
            showMenu();
            getOption();
            if (option == 89) {
                System.out.println("Calculadora finalizada...");
                return;
            }
        } while (option == 78 || option < 49 || option > 54);
        operateWithOption();
        initiateCalculator();
    }

    private void showMenu() {
        /* Método que muestra el menú de todas las operaciones, junto con las indicaciones para salir
         de la aplicación */
        System.out.println("Seleccione una opción: ");
        System.out.println("1) Sumar (A + B)");
        System.out.println("2) Restar (A - B)");
        System.out.println("3) Multiplicar (A * B)");
        System.out.println("4) Dividir (A / B)");
        System.out.println("5) Elevar a (A ^ B)");
        System.out.println("6) Calcular mayor número \n");
        System.out.println("¿Desea salir de la calculadora? (Y/N)\n");
        System.out.print("$:");
    }

    private void getOption() {
        // Obtiene la opcion referente al menu, que operacion elige el usuario. Valida la opcion.
        Scanner sc = new Scanner(System.in);
        String stringOption = sc.next();

        while (stringOption.length() > 1) {
            stringOption = sc.next();
        }

        option = stringOption.charAt(0);
    }

    private void operateWithOption() {
        // Segun la operacion elegida por el usuario, se opera segun corresponda.
        switch (option) {
            case '1':
                sum();
                break;
            case '2':
                substract();
                break;
            case '3':
                multiply();
                break;
            case '4':
                divide();
                break;
            case '5':
                powerNumber();
                break;
            case '6':
                greaterNumber();
                break;
        }
    }

    private void sum() {
        double[] numbers = askForNumbers(false);
        double suma = numbers[0] + numbers[1];
        System.out.println("El total de la suma es: " + suma);
    }

    private void substract() {
        double[] numbers = askForNumbers(false);
        double resta = numbers[0] - numbers[1];
        System.out.println("El total de la resta es: " + resta);
    }

    private void divide() {
        double[] numbers = askForNumbers(true);
        double division = numbers[0] / numbers[1];
        System.out.println("El total de la division es " + division);
    }

    private void multiply() {
        // Pide dos numeros a la función askForNumbers, multiplica los números entregados.
        double[] numbers = askForNumbers(false);
        System.out.println("El total de la multiplicacion es: " + (numbers[0] * numbers[1]));
    }

    private void powerNumber() {
        double[] numbers = askForNumbers(false);
        double result = Math.pow(numbers[0], numbers[1]);
        System.out.println("El resultado es: " + result);
    }

    private void greaterNumber() {
        // Pide dos numeros a la funcion askForNumbers, indica cual es mayor de esos numeros.
        double[] numbers = askForNumbers(false);
        System.out.println(((numbers[0] > numbers[1]) || (numbers[0] == numbers[1]) ? ("El mayor de los " +
                "dos números es " + numbers[0]) : ("El mayor de los dos números es " + numbers[1])));
    }

    private double[] askForNumbers(boolean isDivision) {
        /* Método que pide al usuario dos números en forma de string, comprueba que cada
         String sea un número con el método isNumeric(), y en el caso de que el parametro
         isDivision es verdadero, comprueba también que el número B (dividendo) sea distinto de 0. */
        double[] bothNumbers = new double[2];
        System.out.print("Inserte número A: ");
        String numberA = intro.nextLine();

        while (!isNumeric(numberA)) {
            System.out.println("Error. Inserte número A: ");
            numberA = intro.nextLine();
        }

        System.out.print("Inserte numero B: ");
        String numberB = intro.nextLine();

        while (!isNumeric(numberB) || (isDivision && Double.parseDouble(numberB) == 0)) {
            System.out.println("Error. Inserte numero B: ");
            numberB = intro.nextLine();
        }
        //Convierte los string en Double.
        bothNumbers[0] = Double.parseDouble(numberA);
        bothNumbers[1] = Double.parseDouble(numberB);

        return bothNumbers;
    }

    private boolean isNumeric(String number) {
        // Metodo que comprueba si un string esta solo compuesto por numeros, viendo si cada
        // uno de los caracteres del string es un numero.
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(0) == '-') {
                continue;
            }
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

