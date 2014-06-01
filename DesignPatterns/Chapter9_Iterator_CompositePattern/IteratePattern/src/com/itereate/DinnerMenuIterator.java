package com.itereate;

public class DinnerMenuIterator implements MyIterator{

	MenuItem[] items;
	int position = 0;
	
	public DinnerMenuIterator(MenuItem[] items){
		this.items = items;
	}
	
	public boolean hasNext(){
		if(position >= items.length || items[position] == null){
			return false;
		}else{
			return true;
		}
	}
	
	public Object next(){
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}	
}
