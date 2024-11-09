package org.example;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> st=new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.forEach(s -> System.out.print(s+" "));
        st.pop();
        st.
        st.forEach(s -> System.out.print(s+" "));
        System.out.println(st.peek());
    }
}