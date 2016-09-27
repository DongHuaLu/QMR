package com.game.gem.manager;

import java.util.Comparator;

import com.game.gem.struts.Gem;

@SuppressWarnings("rawtypes")
public class GemSort implements Comparator {
	
	public int compare(Object arg0, Object arg1) {
		Gem data1=(Gem)arg0;
		Gem data2=(Gem)arg1;
		
		if(data1.getIsact() > data2.getIsact()){
			 return 1;
		}
		
		if (data1.getIsact() == data2.getIsact()) {
			if(data1.getLevel() > data2.getLevel()){
				return 1;
			}
	
			if(data1.getLevel() == data2.getLevel()){
				if (data1.getGrid() > data2.getGrid()) {
					return 1;
				}
			}
		}
		return 0 ;	  
	 }
}
