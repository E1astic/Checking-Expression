package org.example;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        
        //  ОСТАЛОСЬ НАЙТИ И ОБРАБОТАТЬ НЕКОРРЕКТНЫЕ СЛУЧАИ (2+3+ и т.д.)
        //  ДОБАВИТЬ ВОЗМОЖНОСТЬ РАБОТЫ С МНОГОРАЗРЯДНЫМИ ЧИСЛАМИ

        Expression expression=new Expression("2*((3+1)");
        try {
            System.out.println(expression.calculate());
        }catch(IncorrectExpressionException e){
            e.printStackTrace();
        }
    }
}