package ru.vvvitas.calculator;

import static ru.vvvitas.calculator.MainActivity.arg;
import static ru.vvvitas.calculator.MainActivity.op;

public class Calculator {
    static String calc(String button, String indicator){
        try {
            if (button.equals("C")) {
                indicator = "0";
                arg = 0;
                op = '.';
            } else
            if (button.equals("+")) {
                if (op == '.' || op == '^') {
                    arg = Float.parseFloat(indicator);
                    indicator = "0";
                }
                op = '+';
            } else
            if (button.equals("-")) {
                if (op == '.' || op == '^') {
                    arg = Float.parseFloat(indicator);
                    indicator = "0";
                }
                op = '-';
            } else
            if (button.equals("X")) {
                if (op == '.' || op == '^') {
                    arg = Float.parseFloat(indicator);
                    indicator = "0";
                }
                op = 'x';
            } else
            if (button.equals(":")) {
                if (op == '.' || op == '^') {
                    arg = Float.parseFloat(indicator);
                    indicator = "0";
                }
                op = ':';
            } else
            if (button.equals("%")) {
                if (op == '.' || op == '^') {
                    arg = Float.parseFloat(indicator);
                    indicator = "0";
                }
                op = '%';
            } else

            if (button.equals("=")) {
                switch (op) {
                    case '+': {
                        arg += Float.parseFloat(indicator);
                        op = '^';
                        indicator = Float.toString(arg);
                        break;
                    }
                    case '-': {
                        arg -= Float.parseFloat(indicator);
                        op = '^';
                        indicator = Float.toString(arg);
                        break;
                    }
                    case 'x': {
                        arg *= Float.parseFloat(indicator);
                        op = '^';
                        indicator = Float.toString(arg);
                        break;
                    }
                    case ':': {
                        arg /= Float.parseFloat(indicator);
                        op = '^';
                        indicator = Float.toString(arg);
                        break;
                    }
                    case '%':
                        op = '^';
                        indicator = Float.toString(arg / 100 * Float.parseFloat(indicator));
                }
            } else

            if (button.equals(".")) {
                if(op == '^'){
                    op = '.';
                    indicator = "0.";
                } else if (!indicator.contains(".") && !indicator.equals("Ё")) {
                            indicator += ".";
                        }
            } else
            if (button.equals("/-/")) {
                if (indicator.startsWith("-"))
                    indicator = indicator.substring(1);
                else if(!indicator.equals("0") && !indicator.equals("Ё")) indicator = "-" + indicator;
            } else
            if (button.equals("<-")) {
                if (indicator.length() == 1) indicator = "0";
                else indicator = indicator.substring(0, indicator.length()-1);
            } else
            if (button.compareTo("0") >= 0 && button.compareTo("9") <= 0) {
                if ((indicator.length() < 10 + (indicator.startsWith("-")?1:0)) || indicator.contains("E")) {
                    if (indicator.equals("0") || op == '^') indicator = button; else indicator += button;
                    if (op == '^') op = '.';
                }
            }
        } catch ( Exception e ) { indicator = "Ё";
                                  op = '^';
                                }
        finally {
            return indicator;
        }
    }

}

