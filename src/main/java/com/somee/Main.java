package com.somee;

import com.somee.tests.*;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<Class<?>> classes = new ArrayList<>();
        classes.add(RegisterTest.class);
        classes.add(LoginTest.class);
        classes.add(LogoutTest.class);
        classes.add(CartTest.class);
        classes.add(OrderTest.class);
        classes.add(CategoryTest.class);
        for(int i = 0; i < 6; i++) {
            testng.setTestClasses(classes.toArray(new Class[i]));
        }
        System.out.println("Bắt đầu chạy các TestNG tests.");
        testng.run();
        System.out.println("Các TestNG tests đã hoàn thành.");
    }
}