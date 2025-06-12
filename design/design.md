# Design

Pseudocode for algorithm:

```
func evaluate(operand1, operator, operand2):
    if operator is "+":
        return operand1 + operand2
    else if operator is "-":
        return operand1 - operand2
    else if operator is "/":
        return operand1 / operand2
    else if operator is "*":
        return operand1 * operand2
    else
        throw Error()

func calculate(expression):
    tokenised_expression = tokenise(expression) // splits expression into its various components: 
                                                // digit, operator, or right bracket. ignores any other character

    for token in tokenised_expression:
        if token is digit:
            push token to operand stack
        else if token is operator:
            push token to operator stack
        else if token is right bracket:
            op1 = pop operand stack
            op2 = pop operand stack
            oper = pop operator stack
            result = evaluate (op1, oper, op2)
            push result to operand stack

    result = pop operand stack
    return result
```