//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
//        try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое число(от 0 до 10 или от I до x), оператор и второе число(от 0 до 10 или от I до x)");
        String calculation = scanner.nextLine();
        String result = calc(calculation);
        System.out.println(result);
//        } catch (IOException e) {
//            System.out.println("Произошла ошибка ввода-вывода: " + e.getMessage());
//        }
    } 

    public static String calc(String calculation) throws IOException {
        String result = "";
        if (calculation.isEmpty()) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("Просьба внести данные");
            }
        }


        char firstcalculation = calculation.charAt(0);

        char lastcalculation = calculation.charAt(calculation.length() - 1);

        if ((!(Character.isLetter(lastcalculation) || (Character.isDigit(lastcalculation)))) || (!(Character.isLetter(firstcalculation) || (Character.isDigit(firstcalculation))))) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("Формат математической операции не удовлетворяет заданию");

            }
        }


        String number34 = calculation.replaceAll("\\s+", "");

        String[] numbers = number34.split("[+\\-/*]");

        Pattern pattern = Pattern.compile("[+\\-/*]");
        Matcher matcher = pattern.matcher(number34);
        List<String> operation = new ArrayList<>();
        while (matcher.find()) {
            operation.add(matcher.group());
        }
        String operationstring = operation.toString();


        if (numbers.length != 2) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("Формат математической операции не удовлетворяет заданию.");
            }
        }
        String number1String = numbers[0];
        String number2String = numbers[1];

        int romearabic1 = 0;
        int romearabic2 = 0;

        boolean allDigits1 = true;

        for (int i = 0; i < number1String.length(); i++) {
            char currentChar = number1String.charAt(i);

            if (!Character.isDigit(currentChar)) {
                allDigits1 = false;
                break; // Если найден нецифровой символ, выходим из цикла
            }
        }
        if (allDigits1) {
            romearabic1 = 1;
        } else {
            romearabic1 = 2;
        }

        boolean allDigits2 = true;

        for (int i = 0; i < number2String.length(); i++) {
            char currentChar = number2String.charAt(i);

            if (!Character.isDigit(currentChar)) {
                allDigits2 = false;
                break; // Если найден нецифровой символ, выходим из цикла
            }
        }
        if (allDigits2) {
            romearabic2 = 1;
        } else {
            romearabic2 = 2;
        }

        if (romearabic1 != romearabic2) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("Укажи только одну систему счисления");

            }
        }
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        int arabicNumber1 = 0;
        if (romearabic1 == 2) {

            for (int i = 0; i < symbols.length; i++) {
                while (number1String.startsWith(symbols[i], index)) {
                    arabicNumber1 += values[i];
                    index += symbols[i].length();
                }


            }
        } else {
            arabicNumber1 = Integer.parseInt(number1String);
        }


        // int arabicNumber11 = arabicNumber1;
        int arabicNumber2 = 0;
        if (romearabic2 == 2) {
            int[] values1 = {100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] symbols1 = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int index1 = 0;

            for (int j = 0; j < symbols1.length; j++) {
                while (number2String.startsWith(symbols1[j], index1)) {
                    arabicNumber2 += values1[j];
                    index1 += symbols1[j].length();
                }


            }

        } else {
            arabicNumber2 = Integer.parseInt(number2String);
        }

        if ((romearabic1 == 2 && arabicNumber1 == 0) || (romearabic2 == 2 && arabicNumber2 == 0)) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("Формат математической операции не удовлетворяет заданию");
            }
        }
        if ((arabicNumber1 < 0 || arabicNumber1 > 10) || (arabicNumber2 < 0 || arabicNumber2 > 10)) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("Просьба указать числа от 1 до 10 или от I до X");

            }
        }
        int answ = 0;

        if (operationstring.equals("[+]")) {
            answ = arabicNumber1 + arabicNumber2;
        }
        if (operationstring.equals("[-]")) {
            answ = arabicNumber1 - arabicNumber2;
        }
        if (operationstring.equals("[*]")) {
            answ = arabicNumber1 * arabicNumber2;
        }
        if (operationstring.equals("[/]") && arabicNumber2 != 0) {
            answ = arabicNumber1 / arabicNumber2;
        }
        if (operationstring.equals("[/]") && arabicNumber2 == 0) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("На ноль делить нельзя");

            }
        }
        if (romearabic1 == 2 && answ < 1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new IOException("В Римской системе нет нуля и отрицательных цисел");

            }
        }
        StringBuilder romanAnswer = new StringBuilder();
        if (romearabic1 == 2) {
//            int[] values1 = {100, 90, 50, 40, 10, 9, 5, 4, 1};
//            String[] symbols1 = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
//            int index1 = 0;
            for (int i = 0; i < values.length; i++) {
                while (answ >= values[i]) {
                    romanAnswer.append(symbols[i]);
                    answ -= values[i];
                }
            }
            result = ("Ответ: " + romanAnswer);
        } else {
            result = ("Ответ: " + Integer.toString(answ));
        }
        return result;
    }
}