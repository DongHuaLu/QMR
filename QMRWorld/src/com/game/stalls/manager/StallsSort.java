package com.game.stalls.manager;
import java.util.Comparator;

import com.game.stalls.bean.StallsBriefInfo;
// 0，摊位星级，1，玩家等级，2，物品数量，3在售货币

//按照摊位星级进行排序：
//点击后，摊位星级高的排在前面；
//星级相同则排物品数量，物品数量多的排前面；
//物品数量相同则排摊位号，摊位号小的排前面。

@SuppressWarnings("rawtypes")
public class StallsSort implements Comparator {
	public int compare(Object arg0, Object arg1) {
		StallsBriefInfo data1=(StallsBriefInfo)arg0;
		StallsBriefInfo data2=(StallsBriefInfo)arg1;
		 if(data1.getStallslv() < data2.getStallslv()){
			 return 1;
		 }
		 
		 if(data1.getStallslv() == data2.getStallslv()){
			if (data1.getSellgoodsnum() < data2.getSellgoodsnum()) {
				return 1;
			}
		 }
		return 0 ;	  
	 }
}


//按照等级进行排序：
//点击后等级高的排前面；
//等级相同则排物品数量，物品数量多的排前面；
//物品数量相同则排摊位号，摊位号小的排前面。

@SuppressWarnings("rawtypes")
class StallsSort1 implements Comparator {
	public int compare(Object arg0, Object arg1) {
		StallsBriefInfo data1=(StallsBriefInfo)arg0;
		StallsBriefInfo data2=(StallsBriefInfo)arg1;
		 if(data1.getPlayerlv() < data2.getPlayerlv()){
			 return 1;
		 }
		 
		 if(data1.getPlayerlv() == data2.getPlayerlv()){
			if (data1.getSellgoodsnum() < data2.getSellgoodsnum()) {
				return 1;
			}
		 }
		return 0 ;	  
	 }
}



//按照物品数量排序：
//点击后物品数量多的排前面；
//物品数量相同则排等级，等级高的排前面；
//等级相同则排摊位号，摊位号小的排前面。

@SuppressWarnings("rawtypes")
class StallsSort2 implements Comparator {
	public int compare(Object arg0, Object arg1) {
		StallsBriefInfo data1=(StallsBriefInfo)arg0;
		StallsBriefInfo data2=(StallsBriefInfo)arg1;
		 if(data1.getSellgoodsnum() < data2.getSellgoodsnum()){
			 return 1;
		 }
		 
		 if(data1.getSellgoodsnum() == data2.getSellgoodsnum()){
			if (data1.getPlayerlv() < data2.getPlayerlv()) {
				return 1;
			}
		 }
		return 0 ;	  
	 }
}

//按照在售货币排序：
//有元宝出售的排前面，都有元宝出售的摊位号小的排前面。
//其次为有元宝出售的排前面，都有元宝出售的摊位号小的排前面。
//最后为两者都没有的摊位，仍然是摊位号小的排前面。


@SuppressWarnings("rawtypes")
class StallsSort3 implements Comparator {
	public int compare(Object arg0, Object arg1) {
		StallsBriefInfo data1=(StallsBriefInfo)arg0;
		StallsBriefInfo data2=(StallsBriefInfo)arg1;
		 if(data1.getSellyuanbao() < data2.getSellyuanbao()){
			 return 1;
		 }
		 
		 if(data1.getSellyuanbao() == data2.getSellyuanbao()){
			if (data1.getSellgold() < data2.getSellgold()) {
				return 1;
			}
		 }
		return 0 ;	  
	 }
}

//按照等级进行排序：
//点击后等级低的排前面；
//等级相同则排物品数量，物品数量多的排前面；
//物品数量相同则排摊位号，摊位号小的排前面。

@SuppressWarnings("rawtypes")
class StallsSort4 implements Comparator {
	public int compare(Object arg0, Object arg1) {
		StallsBriefInfo data1=(StallsBriefInfo)arg0;
		StallsBriefInfo data2=(StallsBriefInfo)arg1;
		 if(data1.getPlayerlv() > data2.getPlayerlv()){
			 return 1;
		 }
		 
		 if(data1.getPlayerlv() == data2.getPlayerlv()){
			if (data1.getSellgoodsnum() < data2.getSellgoodsnum()) {
				return 1;
			}
		 }
		return 0 ;	  
	 }
}




//在搜索物品时，摊位的排序规则如下：
//优先按照摊位星级进行排序，
//摊位星级相同，则按照所搜索的物品数量进行排序。
//若物品数量相同，则按照玩家等级进行排序。
//等级相同，则排摊位号，摊位号小的排在前面。

@SuppressWarnings("rawtypes")
class SearchSort implements Comparator {
	public int compare(Object arg0, Object arg1) {
		StallsBriefInfo data1=(StallsBriefInfo)arg0;
		StallsBriefInfo data2=(StallsBriefInfo)arg1;
		 if(data1.getStallslv() < data2.getStallslv()){
			 return 1;
		 }
		 
		 if(data1.getStallslv() == data2.getStallslv()){
			if (data1.getSellgoodsnum() < data2.getSellgoodsnum()) {
				return 1;
			}
			if (data1.getSellgoodsnum() == data2.getSellgoodsnum()) {
				if(data1.getPlayerlv() < data2.getPlayerlv()) {
					return 1 ;
				}
			}
		 }
		return 0 ;	  
	 }
}






