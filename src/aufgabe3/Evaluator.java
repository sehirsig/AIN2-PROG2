/*
 * class Evaluator
 * repl-Schleife: lese von der Konsole eine Ziele und 
 * werte sie als arithmetischen Ausdruck aus.
 * Da tokenizer String-Konstante zurückliefert, reicht 
 * Gleichheitsprüfung mit == aus (siehe z.B. shift-Methode).
 *
 * Ihr Name: Sebastian Hirsig
 * Datum: 08.11.2020
 */
package aufgabe3;

import java.util.Arrays;
import java.util.Scanner;
import static aufgabe3.Tokenizer.*;

/**
 * Klasse zum Auswerten von arithmetischen Ausdrücken.
 */
public class Evaluator {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static Object[] stack = new Object[10];		// Stack
    private static int top = -1;					// Index des obersten Kellerelements
    private static Object token;					// Aktuelles Token
    private static Tokenizer tokenizer;				// Zerlegt String-Eingabe in Tokens

    private static boolean flag = false;
    private static boolean flag2 = false;

    /**
     * Wertet expr als arithmetischen Ausdruck aus.
     *
     * @param expr Arthmetischer Ausdruck als String
     * @return Wert des Ausdrucks oder null, falls der Ausdruck fehlerhaft ist.
     */
    public static Double eval(String expr) {
        // Dollar in leeren Stack ablegen:
        top = -1;
        stack[++top] = DOLLAR;

        // expr in Tokens zerlegen und erstes Token abholen:
        tokenizer = new Tokenizer(expr);
        token = tokenizer.nextToken();


        while (token != null) {
            // Ihr Code:
            shift();
            reduce();
            if(isOp(stack[top]) && isOp(token)) {
                return null;
            }
            if(flag2)
                flag = true;
            token = tokenizer.nextToken();
            /*if (isOp(stack[top]) && token == DOLLAR)
                flag = true;*/
            flag2 = true;
        }
        if (flag == true) {
            flag = false;
            return null;
        }
        String end = stack[1].toString();
        top = -1;
        return Double.parseDouble((end));

    }

    private static boolean shift() {
        if (top > 0 && token == KL_ZU) {
            int countAuf = 0;
            int countZu = 0;
            for (int i = 0; i < top; i++) {
                if (stack[i] == KL_AUF)
                    countAuf++;
                else if (stack[i] == KL_ZU)
                    countZu++;
            }
            if (countAuf <= countZu) {
                flag = true;
            }
        }
        if (stack[top] == DOLLAR && (token == KL_AUF || isVal(token))) {		// Regel 1 der Parser-Tabelle
            doShift();
            return true;
        } else if (isOp(stack[top]) && (token == KL_AUF || isVal(token))) {            // Regel 2
            doShift();
            return true;
        }else if (stack[top] == KL_AUF && (token == KL_AUF || isVal(token))) {        // Regel 3
            doShift();
            return true;
        }else if (top > 0) {
            if (stack[top - 1] == DOLLAR && isVal(stack[top]) && isOp(token)) {        // Regel 6
                doShift();
                return true;
            }else if (stack[top - 1] == KL_AUF && isVal(stack[top]) && (token == KL_ZU || isOp(token))) {        // Regel 7
                doShift();
                return true;
            }
        }

        return false;
    }

    private static void doShift() {
        // Ihr Code:
        // ...
        flag2 = false;
        if (top == stack.length - 1) {
            stack = Arrays.copyOf(stack, 2*top);
        }
        stack[++top] = token;
        token = tokenizer.nextToken();
        if (isOp(stack[top]) && token == DOLLAR)
            flag = true;
        shift();
        reduce();
    }

    private static boolean isOp(Object o) {
        return (o == PLUS || o == MULT || o == POWER);
    }

    private static boolean isVal(Object o) {
        return o instanceof Double;
    }

    private static boolean reduce() {
        if(top > 1) {
            if (stack[top - 2] == KL_AUF && isVal(stack[top - 1]) && stack[top] == KL_ZU && (token == KL_ZU || isOp(token) || token == DOLLAR)) {        // Regel 4
                doReduceKlValKl();
            }

            if (top > 1 && isVal(stack[top - 2]) && isOp(stack[top - 1]) && isVal(stack[top]) && (token == KL_ZU || token == DOLLAR)) {                             // Regel 8
                doReduceValOpVal();
            }
            if (top > 1 && isVal(stack[top - 2]) && isOp(stack[top - 1]) && isVal(stack[top]) && isOp(token)) {                             // Regel 9
                if (token == PLUS && stack[top - 1] == MULT) {
                    doReduceValOpVal();
                } else if ((token == PLUS || token == MULT) && stack[top - 1] == POWER) {
                    doReduceValOpVal();
                } else if ((token == stack[top - 1])) {
                    doReduceValOpVal();
                } else {
                    doShift();
                }
            }
        }
        // Ihr Code:
        // ...
        return false;
    }

    private static void doReduceKlValKl() {
        // Ihr Code:
        // ...
        stack[top - 2] = stack[top - 1];
        stack[top - 1] = null;
        stack[top] = null;
        int i = top - 1;
        while (stack[i + 2] != null) {
            stack[i] = stack[i + 2];
            i++;
        }
        top = top - 2;
        reduce();
        shift();
    }

    private static void doReduceValOpVal() {
        // Ihr Code:
        // ...
        double a = (double)stack[top - 2];
        double b = (double)stack[top];
        double c;
        if (stack[top - 1].toString() == PLUS) {
            c = a + b;
        } else if (stack[top - 1].toString() == MULT) {
            c = a * b;
        } else {
            c = Math.pow(a,b);
        }

        stack[top - 2] = c;
        stack[top - 1] = null;
        stack[top] = null;
        int i = top - 1;
        while (stack[i + 2] != null) {
            stack[i] = stack[i + 2];
            i++;
        }
        top = top - 2;
        reduce();
        shift();
    }

    private static boolean accept() {
        // Ihr Code:
        // ...
        if (stack[1] == null && token == DOLLAR) // Regel 5
        {
            return true;
        }
        return false;
    }

    /**
     * Liest von der Konsole eine Folge von Zeilen, wertet jede Zeile als
     * Ausdruck aus und gibt seinen Wert aus. (repl = read-evaluate-print-loop).
     */
    public static void repl() {
        Scanner in = new Scanner(System.in);
        System.out.print(ANSI_BLUE + ">> ");


        while (in.hasNextLine()) {
            String line = in.nextLine();
            // Ihr Code:
            // ...
            if(line.equals("end")) {
                System.out.println("bye!");
                break;
            }
            Double s = eval(line);
            if (s == null) {
                System.out.println("!!! error");
            } else {
                System.out.println(s);
            }
            System.out.print(ANSI_BLUE + ">> ");
        }

    }

    /**
     * Testprogramm.
     *
     * @param args wird nicht benutzt.
     */
    public static void main(String[] args) {
        // Zum Testen, später auskommentieren:

        String s1 = "1+2";
		String s2 = "2^10+5";
		String s3 = "5+2^10";
        String s4 = "(2+3*4+4)^2";
        String s5 = "(2+3*4+4))*2";
        String s6 = "2+3**4";
        String s7 = "2^2^3";
        String s8 = "2^2*5";
        String s9 = "1+(2+(3+(4+(5+6))))";
        String s10 = "1+2+3+4+5+6";

		System.out.println(eval(s1));	// 3.0
		System.out.println(eval(s2));	// 1029.0
        System.out.println(eval(s3));	// 1029.0
        System.out.println(eval(s4));	// 324.0
        System.out.println(eval(s5));	// null; Syntaxfehler
        System.out.println(eval(s6));   // null; Syntaxfehler
        System.out.println(eval(s7));	// 64.0
        System.out.println(eval(s8));	// 20.0
        System.out.println(eval(s9));	// 21.0
        System.out.println(eval(s10));	// 21.0

        repl();
    }
}
