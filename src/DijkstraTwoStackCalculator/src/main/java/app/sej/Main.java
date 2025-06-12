package app.sej;

import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    private final ArrayList<Integer> operandStack;
    private final ArrayList<Character> operatorStack;

    public Main()
    {
        operandStack = new ArrayList<>();
        operatorStack = new ArrayList<>();
    }

    private int evaluate(int firstOperand, char operator, int secondOperand)
    {
        return switch (operator) {
            case '+' -> firstOperand + secondOperand;
            case '-' -> firstOperand - secondOperand;
            case '/' -> firstOperand / secondOperand;
            case '*' -> firstOperand * secondOperand;
            default -> throw new RuntimeException("Malformed Expression (unknown operator)");
        };
    }

    private void evaluateOperator()
    {
        if (operandStack.size() < 2)
        {
            throw new RuntimeException("Malformed Expression (operator doesn't have enough operands)");
        }

        int operand2 = operandStack.removeLast();
        int operand1 = operandStack.removeLast();
        char operator = operatorStack.removeLast();

        int result = evaluate(operand1, operator, operand2);
        operandStack.add(result);
    }

    private int calculate(String expression)
    {
        String tempOperand = "";

        for (int i = 0; i < expression.length(); i++)
        {
            char character = expression.charAt(i);

            if (Character.isDigit(character))
            {
                tempOperand += character;
            } else
            {
                if (!tempOperand.isEmpty())
                {
                    int operand = Integer.parseInt(tempOperand);
                    operandStack.add(operand);
                    tempOperand = "";
                }

                if (character == '+' || character == '-' || character == '/' || character == '*')
                {
                    operatorStack.add(character);
                } else if (character == ')')
                {
                    evaluateOperator();
                }

            }
        }

        if (!tempOperand.isEmpty())
        {
            int operand = Integer.parseInt(tempOperand);
            operandStack.add(operand);
        }

        while (!operatorStack.isEmpty())
        {
           evaluateOperator();
        }

        return operandStack.removeLast();
    }

    public static void test(String name, int got, int expected)
    {
        System.out.printf("%-40.40s : %-20.20s\n", name, (got == expected ? "passed" : "failed (got " + got + ")") );
    }

    public static void main(String[] args)
    {
        Main main = new Main();

        test("( 3 + 5 + ( 9 - 3 ) ) = 14", main.calculate("( 3 + 5 + ( 9 - 3 ) )"), 14);
        test("( 1 + 2 ) * 3 = 9", main.calculate("( 1 + 2 ) * 3"), 9);
        test("(1 + ((2 + 3) * ( 4 * 5))) = 101", main.calculate("(1 + ((2 + 3) * ( 4 * 5)))"), 101);
    }

}
