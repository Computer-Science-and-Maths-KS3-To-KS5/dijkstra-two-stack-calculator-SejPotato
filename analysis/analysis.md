# Analysis

My understanding of the program is the following: 
- There is a string with an expression present, and my program reads the string from left to right. 
- If there is an operator, it will push it onto the operator stack, and if there is an operand, it will push it onto the operand stack.
- When the program encounters a right bracket, it will pop the last two operands off the operand stack and the last operator off the operator stack. The program will then evaluate this expression, and then push the result back on the operand stack.<br><br>

At the end of the process, there will only be one value at the top of the operand stack, and that will be the final result.<br><br>

According to my algorithm, this means, however, that all the expressions must be fully bracketed, as my algorithm has no idea of a precedence ranking between operators, or an order of operations.