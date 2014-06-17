
import java.util.*;

public class Fruit implements Comparable<Fruit>{

        private String fruitName;
        private String fruitDesc;
        private int quantity;

        public Fruit(String fruitName, String fruitDesc, int quantity) {
                super();
                this.fruitName = fruitName;
                this.fruitDesc = fruitDesc;
                this.quantity = quantity;
        }

        public String getFruitName() {
                return fruitName;
        }
        public void setFruitName(String fruitName) {
                this.fruitName = fruitName;
        }
        public String getFruitDesc() {
                return fruitDesc;
        }
        public void setFruitDesc(String fruitDesc) {
                this.fruitDesc = fruitDesc;
        }
        public int getQuantity() {
                return quantity;
        }
        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

	@Override
	public int compareTo(Fruit compareFruit){

		// ascending order
		return this.quantity - compareFruit.getQuantity();

		// descending order
		//return compareFruit.getQuantity() - this.quantity;
		
	}

	public static Comparator<Fruit> FruitNameComparator = new Comparator<Fruit>(){
		
		public int compare(Fruit f1, Fruit f2){
			String fruitName1 = f1.getFruitName().toUpperCase();
		        String fruitName2 = f2.getFruitName().toUpperCase();	

			 //ascending order
	      		return fruitName1.compareTo(fruitName2);
 
	      		//descending order
	      		//return fruitName2.compareTo(fruitName1);
		}
	};
}

