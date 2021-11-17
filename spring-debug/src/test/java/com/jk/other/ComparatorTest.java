package com.jk.other;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

	public static void main(String[] args) {
		A a1 = new A("a", 1);
		A a2 = new A("b", 2);
		List<A> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);

		System.out.println("before sort: " + list);
		list.sort(new AgeSort());
		System.out.println("after sort age: " + list);
		list.sort(new NameSort());
		System.out.println("after sort name: " + list);
	}
}

/**
 * age 逆序
 */
class AgeSort implements Comparator<A> {

	@Override
	public int compare(A o1, A o2) {
		return o2.getAge() - o1.getAge();
	}
}

/**
 * name 顺序
 */
class NameSort implements Comparator<A> {

	@Override
	public int compare(A o1, A o2) {
		return o1.getName().compareTo(o2.getName());
	}
}

@Data
@AllArgsConstructor
class A {
	private String name;
	private int age;
}