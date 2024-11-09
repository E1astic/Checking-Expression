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

}
