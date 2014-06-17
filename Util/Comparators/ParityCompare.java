
import java.util.*;

public class ParityCompare {

    static boolean even_first = true;

    public static Comparator<Integer> parityComparator = new Comparator<Integer>(){
	@Override
    	public int compare(Integer i1, Integer i2){
		/* Check if two even or two odd values */
		if((i1+i2) % 2 == 0){
			return 0;
		/* First even values */
		} else if (i1 % 2 == 0){
			return even_first ? -1 : 1;
		/* Second odd values */
		} else{
			return even_first ? 1 : -1;
		}
	}
    };

    public static void main(String[] args) {

	List<Integer> list = new ArrayList<Integer>(Arrays.asList(-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10));
	/* Natural order */
	//System.out.println("Natural order:");
	//for(Integer val: list){
	//	System.out.println(val);
	//}
	/* Reverse order */
	//System.out.println("Reverse order:");
	Collections.sort(list, Collections.reverseOrder());
	//for(Integer val: list){
	//	System.out.println(val);
	//}
	/* Even, odd order */
	Collections.sort(list);
	System.out.println("First even, later odd values:");
	Collections.sort(list, ParityCompare.parityComparator);
	for(Integer val: list){
		System.out.println(val);
	}
	System.out.println("First odd, later even values:");
	even_first = false; // change order
	Collections.sort(list, ParityCompare.parityComparator);
	for(Integer val: list){
		System.out.println(val);
	}

    }
}
