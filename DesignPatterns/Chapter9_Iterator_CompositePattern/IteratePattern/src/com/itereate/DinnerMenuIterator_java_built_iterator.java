package com.itereate;

import java.util.Iterator;

public class DinnerMenuIterator_java_built_iterator implements Iterable, Menu{

	MenuItem[] items;
	int position = 0;
	
	public DinnerMenuIterator_java_built_iterator(MenuItem[] items){
		this.items = items;
	}
	
	@Override
	public Iterator iterator() {
	        Iterator it = new Iterator() {
	        	@Override
	        	public boolean hasNext(){
	        		if(position >= items.length || items[position] == null){
	        			return false;
	        		}else{
	        			return true;
	        		}
	        	}
	        	@Override
	        	public Object next(){
	        		MenuItem menuItem = items[position];
	        		position = position + 1;
	        		return menuItem;
	        	}	
	
	        	@Override
	        	public void remove(){
	        		assert(position>0);
	        		if(items[position-1] != null){
	        			for(int i=position-1; i < (items.length -1); i++){
	        				items[i]=items[i+1];
	        			}
	        			items[items.length-1] = null; 
	        		}
	        	}
	        };
	        return it;
	}
}
