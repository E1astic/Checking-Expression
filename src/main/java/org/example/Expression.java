package org.example;

import java.util.Stack;

public class Expression {
    private String expression;

    public Expression(){}
    public Expression(String expression){
        this.expression=expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean checkBrackets(){
        Stack<Character> brackets=new Stack<>();

        for(int i=0;i<expression.length();++i){
            char symb=expression.charAt(i);
            if(symb=='(' || symb=='[')
                brackets.push(symb);
            else if(symb==')'){
                if(brackets.size()==0 || brackets.peek()!='(')
                    return false;
                brackets.pop();
            }
            else if(symb==']'){
                if(brackets.size()==0 || brackets.peek()!='[')
                    return false;
                brackets.pop();
            }
        }

        if(brackets.size()!=0)
            return false;
        return true;
    }

    public int calculate() throws IncorrectExpressionException {
        if(!checkBrackets())
            throw new IncorrectExpressionException("В выражении некорректно проставлены скобки.");

        Stack<Character> signs=new Stack<>();  // скобки и арифметические знаки
        Stack<Integer> nums=new Stack<>();  // числа

        for(int i=0;i<expression.length();++i){
            char symb=expression.charAt(i);

            if(symb=='(' || symb=='[' || symb=='*' || symb=='+' || symb=='-'){
                // если арифметический знак в начале выражения или перед арифметическим знаком стоит не число
                if(((symb=='*' || symb=='+' || symb=='-') && i==0) ||
                        (symb=='*' || symb=='+' || symb=='-') && i!=0 &&
                        (expression.charAt(i-1)=='*' || expression.charAt(i-1)=='+' || expression.charAt(i-1)=='-'
                         || expression.charAt(i-1)=='('|| expression.charAt(i-1)=='[')) {
                    break;
                }
                signs.add(symb);
            }
            // если это число
            else if(Character.isDigit(symb)){
                StringBuilder number=new StringBuilder("");
                // читаем цифры числа, если оно многоразрядное
                while(i<expression.length() && Character.isDigit(expression.charAt(i))){
                    number.append(expression.charAt(i));
                    ++i;
                }
                --i;
                int num=Integer.parseInt(number.toString());
                // если перед полученным числом стоит умножение, то выполняем умножение
                if(signs.size()!=0 && signs.peek()=='*') {
                    signs.pop();
                    int a = nums.pop();
                    nums.add(a * num);
                }
                // иначе просто добавляем число
                else{
                    nums.add(num);
                }
            }
            // если это закрывающая скобка, то выполним вычисление всей скобки
            else if(symb==')'){
                if(expression.charAt(i-1)=='*' || expression.charAt(i-1)=='+' || expression.charAt(i-1)=='-')
                    break;
                while(signs.peek()!='('){
                    int b=nums.pop();
                    int a=nums.pop();
                    if(signs.peek()=='+')
                        nums.add(a+b);
                    else if(signs.peek()=='-')
                        nums.add(a-b);
                    else if(signs.peek()=='*')
                        nums.add(a*b);
                    signs.pop();  // удаляем арифметический знак
                }
                signs.pop();  // удаляем открывающую скобку

                // если перед нашим вновь добавленным числом стоит знак умножения, то выполним умножение
                if(signs.size()!=0 && signs.peek()=='*'){
                    signs.pop();
                    int b=nums.pop();
                    int a=nums.pop();
                    nums.add(a*b);
                }
            }
            // делаем то же самое для квадратной скобки
            else if(symb==']'){
                if(expression.charAt(i-1)=='*' || expression.charAt(i-1)=='+' || expression.charAt(i-1)=='-')
                    break;
                while(signs.peek()!='['){
                    int b=nums.pop();
                    int a=nums.pop();
                    if(signs.peek()=='+')
                        nums.add(a+b);
                    else if(signs.peek()=='-')
                        nums.add(a-b);
                    else if(signs.peek()=='*')
                        nums.add(a*b);
                    signs.pop();  // удаляем арифметический знак
                }
                signs.pop();  // удаляем открывающую скобку

                // если перед нашим вновь добавленным числом стоит знак умножения, то выполним умножение
                if(signs.size()!=0 && signs.peek()=='*'){
                    signs.pop();
                    int b=nums.pop();
                    int a=nums.pop();
                    nums.add(a*b);
                }
            }
            // если встретили любой другой неизвестный символ
            else{
                break;
            }
        }
        // выполняем вычисления всех оставшихся компонентов
        while(nums.size()>=2 && signs.size()>=1){
            int b=nums.pop();
            int a=nums.pop();
            if(signs.pop()=='+')
                nums.add(a+b);
            else
                nums.add(a-b);
        }
        if(signs.size()!=0 || nums.size()!=1)
            throw new IncorrectExpressionException("Данное выражение некорректно. Оно не может быть вычислено.");

        return nums.peek();
    }
}
