
import java.util.*;

public class HelloWorld {

    public static Comparator<Integer> parityComparator = new Comparator<Integer>(){

    	public int compare(Integer i1, Integer i2){

		/* Check if two even or two odd values */
		if((i1+i2) % 2 == 0){
			return 0;
		/* First even values */
		} else if (i1 % 2 == 0){
			return -1;
		/* Second odd values */
		} else{
			return 1;
		}
	}
    };

    public static void main(String[] args) {

	System.out.println(" --------------------- Sorting Array ---------------------------");
	String[] fruits = new String[] {"Pineapple","Apple", "Orange", "Banana"}; 
 
	Arrays.sort(fruits);
 
	int i=0;
	for(String temp: fruits){
		System.out.println("fruits " + ++i + " : " + temp);
	}
	System.out.println(" -------------------- END Sorting Array ---------------------------");

	System.out.println(" -------------------- Sorting Array List --------------------------");
	List<String> fruitsList = new ArrayList<String>();
 
	fruitsList.add("Pineapple");
	fruitsList.add("Apple");
	fruitsList.add("Orange");
	fruitsList.add("Banana");
 
	Collections.sort(fruitsList);
 
	i=0;
	for(String temp: fruitsList){
		System.out.println("fruits " + ++i + " : " + temp);
	}
	System.out.println(" ----------------- END Sorting Array List --------------------------");


	System.out.println(" -------------------- Sorting witch Comparable --------------------------");
		Fruit[] fruitsObject = new Fruit[4];
 
		Fruit pineappale = new Fruit("Pineapple", "Pineapple description",70); 
		Fruit apple = new Fruit("Apple", "Apple description",100); 
		Fruit orange = new Fruit("Orange", "Orange description",80); 
		Fruit banana = new Fruit("Banana", "Banana description",90); 
 
		fruitsObject[0]=pineappale;
		fruitsObject[1]=apple;
		fruitsObject[2]=orange;
		fruitsObject[3]=banana;
		
		System.out.println(" - Sorting by Fruit quantity [Comparable: Arrays.sort(fruitsObject)]"); 
		Arrays.sort(fruitsObject);
 
		i=0;
		for(Fruit temp: fruitsObject){
		   System.out.println("fruits " + ++i + " : " + temp.getFruitName() + 
			", Quantity : " + temp.getQuantity());
		}
		System.out.println(" - Sorting by Fruit name [Comparator: Arrays.sort(fruitsObject, Fruit.FruitNameComparator)]"); 
		Arrays.sort(fruitsObject, Fruit.FruitNameComparator);
 
		i=0;
		for(Fruit temp: fruitsObject){
		   System.out.println("fruits " + ++i + " : " + temp.getFruitName() + 
			", Quantity : " + temp.getQuantity());
		}

	System.out.println(" -------------------- END Sorting witch Comparable ----------------------");

	System.out.println(" -------------------- Sorting Table parity & noparity --------------------------");

	List<Integer> list = new ArrayList<Integer>(Arrays.asList(-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10));
	System.out.println("Natural order:");
	for(Integer val: list){
		System.out.println(val);
	}
	System.out.println("Reverse order:");
	Collections.sort(list, Collections.reverseOrder());
	for(Integer val: list){
		System.out.println(val);
	}
	Collections.sort(list);
	System.out.println("First even, later odd values:");
	Collections.sort(list, HelloWorld.parityComparator);
	for(Integer val: list){
		System.out.println(val);
	}


	
	




	



    }
}
