package com.example.gnu.SecurityTest;

public class TestMain {
public static void main(String[] args) {
	TestVO vo = new TestVO();
	TestVO vo2 = vo;
	vo2.age = 10;
	System.out.println(vo.age);
}
}
