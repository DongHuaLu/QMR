package com.game.structs;

import com.game.structs.interfaces.Compare;

public class BinaryHeap {

	//二叉堆默认大小
	private static int DEFAULT_SIZE = 100;
	//二叉堆大小
	private int max = 0;
	//二叉堆大小
	private int size = 0;
	//二叉堆
	private Compare[] array;
	
	public BinaryHeap(){
		this(DEFAULT_SIZE);
	}
	
	public BinaryHeap(int size){
		max = size;
		array = new Compare[size];
	}
	
	public void add(Compare c){
		if(size >= max) return;
		
		array[size] = c;
		size++;
		
		int site = size;
		while(true){
			int father = site >> 1;
			if(father <= 1) return;
			
			if(c.compare(array[father - 1])){
				Compare _c = array[father - 1];
				array[father - 1] = c;
				array[site - 1] = _c;
				site = father;
			}
			else return;
		}
	}
	
	public Compare get(){
		if(size==0) return null;
		
		Compare result = array[0];
		size--;
		array[0] = array[size];
		array[size] = null;
		
		Compare c = array[0];
		if(c==null) return result;
		
		int site = 1;
		while(true){
			int child = site * 2;
			if(child > max) break;
			
			Compare min = array[child - 1];
			if(min==null) break;
			int childSite = child;
			if(array[child]!=null){
				if(!min.compare(array[child])){
					min = array[child];
					childSite = child + 1;
				}
			}
			
			if(min.compare(c)){
				array[site - 1] = array[childSite - 1];
				array[childSite - 1] = c;
				site = childSite;
			}else break;
		}
		
		return result;
	}
	
	public int size(){
		return size;
	}
}
