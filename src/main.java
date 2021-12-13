import java.util.Scanner;

public class main {

    static boolean flag = true;
    static Scanner scan = new Scanner(System.in);
    static String rezalt = "";

    public static void main(String[] args) {

        while (flag) {

            printFirstMenu();

        }
        scan.close();

    }

    private static void printFirstMenu() {
        System.out.println("\n" + "1. Ввести пример\n2. Продолжить работу с предыдущим ответом\n3. Выход");
        menuFirst(scaner());
    }

    private static String scaner() {
       return scan.nextLine();
    }


    private static void menuFirst(String str) {

        int i = Integer.parseInt(str);

        switch (i){
            case 1: {arefmeticsString();break;}
            case 2: {
                if (rezalt.equals("")) {
                    System.out.println("Недоступно!");
                    printFirstMenu(); break;
                }else secondMenu(); break;
            }
            case 3: {exit();}
        }
    }

    private static void secondMenu() {
        System.out.println("1. Сложение\n2. Вычитание\n3. Умножение\n4. Деление\n5. Факториал\n" +
                "6. Возведение с степень\n7. Сравнение\n0. назад");
        String secondMenuChoice = scaner();
        switch (secondMenuChoice) {
            case "1": {plus(rezalt,printSecondNum());break;}
            case "2": {minus(rezalt,printSecondNum());break;}
            case "3": {multiply(rezalt,printSecondNum());break;}
            case "4": {devision(rezalt,printSecondNum());break;}
            case "5": {
                System.out.println(printLastRezalt() + " Факториал числа равен");
                System.out.print("\"!\" " + rezalt + "! = ");
                rezalt = String.valueOf(factorial(Integer.parseInt(rezalt)));
                System.out.print("1 = " + rezalt + "\n");
                break;
            }
            case "6": {
                System.out.println(printLastRezalt() + " в какую степень " +
                        "его возвести? Введите число");
                int num2 = Integer.parseInt(scaner());
                int num1 = Integer.parseInt(rezalt);
                rezalt = String.valueOf(exponentiation(num1, num2));
                System.out.println("\"^\" " + num1 + " ^ " + num2 + " = " + rezalt);
                break;
            }
            case "7": {
                System.out.println(printLastRezalt() + " с каким числом сравнить, введите число");
                compare(rezalt, scaner());
                break;
            }
            case "0": menuFirst("0");
        }
    }

    private static String printLastRezalt() {
        return "Результат пршлого вычисления равен " + rezalt;
    }
    private static String printSecondNum(){
        System.out.println("Результат прошлого вычисления равен " + rezalt + ", Введите воторое число: ");
        return scaner();
    }
    private static String[] arefmeticsString() {
        String[] arrInput = scaner().split(" ");

        for (int i = 0; arrInput.length > i; i++) {
            arrInput[i] = arrInput[i].replaceAll("[,\"]", "");
        }

        if (arrInput.length < 2 ) {
            System.out.println("Неверная строка для вычислений");
            printFirstMenu();
            return arrInput;
        }
        choice(arrInput);
        return arrInput;
    }

    private static void choice(String[] arrInput) {

        for (int i = 0; arrInput.length > i; i++) {
            if (arrInput[i].equals("!")) {
                System.out.print("\"!\" " + arrInput[--i] + "! = ");
                rezalt = String.valueOf(factorial(Integer.parseInt(arrInput[i])));
                System.out.print("1 = " + rezalt + "\n");
                i++;
            }
            if (arrInput[i].equals("+") || arrInput[i].equals("-") || arrInput[i].equals("*") ||
                    arrInput[i].equals("/") || arrInput[i].equals("?") || arrInput[i].equals("^")) {
                switch (arrInput[i]) {
                    case "+":
                        plus(arrInput[--i], arrInput[i += 2]);
                        break;
                    case "-":
                        minus(arrInput[--i], arrInput[i += 2]);
                        break;
                    case "*":
                        multiply(arrInput[--i], arrInput[i += 2]);
                        break;
                    case "/":
                        devision(arrInput[--i], arrInput[i += 2]);
                        break;
                    case "?":
                        compare(arrInput[--i], arrInput[i += 2]);
                        break;
                    case "^":
                        rezalt = String.valueOf(exponentiation(Integer.parseInt(arrInput[--i]),
                                Integer.parseInt(arrInput[i++])));
                        System.out.println("\"^\" " + arrInput[--i] + " ^ " + arrInput[i++] + " = " + rezalt);
                }
            }
        }
    }
    private static int exponentiation(int num1, int num2) {
        if (num2 == 1)
            return num1;
        else
            return num1 * exponentiation(num1, num2 - 1);
    }

    private static int factorial(int str) {
        if (str < 0) {
            System.out.println("Факториала отрицательного числа не бывает");
            printFirstMenu();
        }
        if (str <= 1) {
            return 1;
        }
        System.out.print(str + " * ");
        return str * factorial(str - 1);
    }
    private static void compare(String str1, String str2) {
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        char rez = num1 > num2 ? '>' : '<';
        System.out.println("\"?\" " + num1 + " ? " + num2 + " = " + num1 + " " + rez + " " + num2);
    }
    private static void devision(String str1, String str2) {
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        int rez = num1 / num2;
        System.out.println("\"/\" " + num1 + " " + "/" + " " + num2 + " = " + rez);
        rezalt = Integer.toString(rez);
    }
    private static void multiply(String str1, String str2) {
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        int rez = num1 * num2;
        System.out.println("\"*\" " + num1 + " " + "*" + " " + num2 + " = " + rez);
        rezalt = Integer.toString(rez);
    }
    private static void minus(String str1, String str2) {
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        int rez = num1 - num2;
        System.out.println("\"-\" " + num1 + " " + "-" + " " + num2 + " = " + rez);
        rezalt = Integer.toString(rez);
    }
    private static void plus(String str1, String str2) {
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        int rez = num1 + num2;
        System.out.println("\"+\" " + num1 + " " + "+" + " " + num2 + " = " + rez);
        rezalt = Integer.toString(rez);
    }

    private static void exit() {  flag = false;  }
}
