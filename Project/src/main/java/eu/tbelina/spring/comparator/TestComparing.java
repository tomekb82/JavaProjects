package eu.tbelina.spring.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestComparing {

	public static void main(String[] args) {
		/* COMPARABLE */
		List<ExpenseComparable> expenseCompatableList = new ArrayList<ExpenseComparable>();
		expenseCompatableList.add(new ExpenseComparable(6, "a", 1, 2, new Date()));
		expenseCompatableList.add(new ExpenseComparable(2, "g", 1, 1, new Date()));
		expenseCompatableList.add(new ExpenseComparable(4, "d", 1, 4, new Date()));
		expenseCompatableList.add(new ExpenseComparable(1, "s", 1, 3, new Date()));
		expenseCompatableList.add(new ExpenseComparable(5, "b", 1, 5, new Date()));
		expenseCompatableList.add(new ExpenseComparable(3, "f", 1, 6, new Date()));
		
		System.out.println("Comparable unsorted:");
		for (ExpenseComparable e: expenseCompatableList){
			System.out.println(e.toString());
		}
	
		Collections.sort(expenseCompatableList);
		System.out.println("Comparable sorted by quantity:");
		for (ExpenseComparable e: expenseCompatableList){
			System.out.println(e.toString());
		}
		
		/* COMPARATOR */
		List<ExpenseComparator> expenseCompatorList = new ArrayList<ExpenseComparator>();
		expenseCompatorList.add(new ExpenseComparator(6, "a", 1, 2, new Date()));
		expenseCompatorList.add(new ExpenseComparator(2, "g", 1, 1, new Date()));
		expenseCompatorList.add(new ExpenseComparator(4, "d", 1, 4, new Date()));
		expenseCompatorList.add(new ExpenseComparator(1, "s", 1, 3, new Date()));
		expenseCompatorList.add(new ExpenseComparator(5, "b", 1, 5, new Date()));
		expenseCompatorList.add(new ExpenseComparator(3, "f", 1, 6, new Date()));
		
		System.out.println("Comparator unsorted:");
		for (ExpenseComparator e: expenseCompatorList){
			System.out.println(e.toString());
		}
		
		boolean asc_desc = false;
		Collections.sort(expenseCompatorList, new MyComparator(asc_desc));
		System.out.println("Comparator sorted by id:");
		for (ExpenseComparator e: expenseCompatorList){
			System.out.println(e.toString());
		}

	}

}
