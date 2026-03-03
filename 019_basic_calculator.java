/**
 * Basic Calculator
    Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

    Example 1:
    Input: s = "1 + 1"
    Output: 2
    
    Example 2:
    Input: s = " 2-1 + 2 "
    Output: 3

    Example 3:
    Input: s = "(1+(4+5+2)-3)+(6+8)"
    Output: 23
 */

import java.util.regex.*;

class Solution {
    public int calculate(String s) {

        var value = check(s.replaceAll("\\s+","").split(""));
        
        List<String> auxList = new ArrayList<>();

        Pattern p = Pattern.compile("\\s*(\\(.*? \\(\\(.*? \\).*? \\)\\).*? |[^()]+)\\s*");
        Matcher m = p.matcher(value);
        while (m.find()) {
            auxList.add(m.group(1));
        }

        return eval(String.join("", auxList));
    }

    public String check(String[] values) {
        for (int x = 0; x < values.length; x++) {
            if (values[x].equals("-")) {
                if (x + 1 < values.length) {
                    if (values[x + 1].equals("(")) {
                        values[x] = "-1*";
                    }
                }
            }
        }

        return String.join("", values);
    }

    public static int eval(final String str) {
        return (int) new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}