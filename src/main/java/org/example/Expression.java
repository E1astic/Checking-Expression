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

    public int calculate() throws Exception {
        if(!checkBrackets())
            throw new Exception("Скобки");

        Stack<Character> signs=new Stack<>();  // скобки и арифметические знаки
        Stack<Integer> nums=new Stack<>();  // числа

        for(int i=0;i<expression.length();++i){
            char symb=expression.charAt(i);

            if(symb=='(' || symb=='[' || symb=='*'){
                signs.add(symb);
            }
            else if(Character.isDigit(symb) && signs.size()!=0 && signs.peek()=='*'){
                signs.pop();
                int b=Integer.parseInt(symb+"");
                int a=nums.pop();
                nums.add(a*b);
            }
            else if(Character.isDigit(symb)){
                nums.add(Integer.parseInt(symb+""));
            }
            else if((symb=='+' || symb=='-') && signs.size()!=0 && signs.peek()=='*'){
                signs.pop();
                int b=nums.pop();
                int a=nums.pop();
                nums.add(a*b);
                if(symb=='+')
                    signs.add('+');
                else
                    signs.add('-');
            }
            else if(symb=='+' || symb=='-'){
                signs.add(symb);
            }
            else if(symb==')'){
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
                if(signs.size()!=0 && signs.peek()=='*'){
                    signs.pop();
                    int b=nums.pop();
                    int a=nums.pop();
                    nums.add(a*b);
                }
            }
            else if(symb==']'){
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
                if(signs.size()!=0 && signs.peek()=='*'){
                    signs.pop();
                    int b=nums.pop();
                    int a=nums.pop();
                    nums.add(a*b);
                }
            }
        }
        System.out.println(nums.size()+" "+signs.size());

        while(nums.size()>=2 && signs.size()>=1){
            int b=nums.pop();
            int a=nums.pop();
            if(signs.pop()=='+')
                nums.add(a+b);
            else
                nums.add(a-b);
        }
        if(signs.size()!=0 || nums.size()!=1)
            throw new Exception("Данное выражение некорректно. Оно не может быть вычислено.");

        return nums.peek();
    }
}
