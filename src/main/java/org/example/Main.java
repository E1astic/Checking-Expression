package org.example;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Expression expression=new Expression("2*(3+1)");
        try {
            System.out.println(expression.calculate());
        }catch(Exception e){
            System.out.println("Некорректное выражение");
            e.printStackTrace();
        }
    }
}