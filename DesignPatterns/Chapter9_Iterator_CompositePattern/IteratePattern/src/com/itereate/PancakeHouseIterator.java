package com.itereate;

import java.util.ArrayList;

public class PancakeHouseIterator implements MyIterator{

	ArrayList items;
	int position = 0;
	
	public PancakeHouseIterator(ArrayList items){
		this.items = items;
	}
	
	public boolean hasNext(){
		if(position >= items.size()){
			return false;
		}else{
			return true;
		}
	}
	
	public Object next(){
		MenuItem menuItem = (MenuItem)items.get(position);
		position = position + 1;
		return menuItem;
	}
}
