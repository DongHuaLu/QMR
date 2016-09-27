package com.game.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 随机数工具
 * @author heyang E-mail: szy_heyang@163.com
 *
 */
public class RandomUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RandomUtils.class);

	private static Random random = new Random();
	
	public static int random(int max){
		return random.nextInt(max);
	}
	
	public static int random(int min, int max){
		if(max - min <= 0) return min;
		return min + random.nextInt(max - min + 1);
	}
	

	/**
     * 根据几率  计算是否生成 
     * @param probability
     * @return
     */
    public  static boolean isGenerate(int probability,int gailv)
    {
    	if(gailv==0){gailv=1000;}
    	int random_seed=random.nextInt(gailv+1);
    	return probability>=random_seed;
    }
    
    /**
     * 
     * gailv/probability 比率形式
     * @param probability
     * @param gailv
     * @return
     */
    public  static boolean isGenerate2(int probability,int gailv)
    {
    	if (probability == gailv) return true;
    	if(gailv==0)return false;
    	int random_seed=random.nextInt(probability);
    	return random_seed+1 <= gailv;
    }

    /**
     * 从 min 和 max 中间随机一个值
     * @param max
     * @param min
     * @return 包含min max
     */
    public static int  randomValue(int max,int min)
    {
    	int temp = max - min;
		temp = RandomUtils.random.nextInt(temp + 1);
		temp = temp + min;
		return temp;
    }
    
    /**
     * 返回在0-maxcout之间产生的随机数时候小于num
     * @param num
     * @return
     */
   public static boolean isGenerateToBoolean(float num,int maxcout){
   	double count=Math.random()*maxcout;

   	if(count<num){
   		return true;	
   	}
   	return false;
   }
    
     /**
      * 返回在0-maxcout之间产生的随机数时候小于num
      * @param num
      * @return
      */
    public static boolean isGenerateToBoolean(int num,int maxcout){
    	double count=Math.random()*maxcout;

//    	System.out.println("计算========"+ count);
//    	System.out.println("传入========"+ num);
//    	System.out.println("计算<传入");
//    	System.out.println(count<num);
    	if(count<num){
    		return true;	
    	}
    	return false;
    }
   /**
    * 随机产生min到max之间的整数值 包括min max
    * @param min
    * @param max
    * @return
    */
   public static int randomIntValue(int min,int max){
	   return (int)(Math.random() * (double)(max - min + 1)) + min;  
   }
   
   
   public static float randomFloatValue(float min,float max){
	   return (float)(Math.random() * (double)(max-min)) + min;  
   }
   
   public static Object randomItem(Collection<? extends Object> collection){
	   if(collection==null||collection.size()==0){
		   return null;
	   }
	   int t=(int) (collection.size()*Math.random());
	   int i=0;
	   for(Iterator<? extends Object> item=collection.iterator();i<=t&&item.hasNext();){
		   Object next=item.next();
		   if(i==t){
			   return next;
		   }
		   i++;
	   }
	   return null;
   }
   
   /**
    * 
    * @param param 根据总机率返回序号
    * @return
    */
	public static int randomIndexByProb(List<Integer> probs) {
		try{
			LinkedList<Integer> newprobs=new LinkedList<Integer>();
			for (int i = 0; i < probs.size(); i++) {
//				if (probs.get(i) > 0) {
					if (i == 0) {
						newprobs.add(probs.get(i));
					} else {
						newprobs.add(newprobs.get(i - 1) + probs.get(i));
					}
//				}
			}
			if(newprobs.size()<=0){
				return -1;
			}
			int last = newprobs.getLast();
//			String[] split = last.split(Symbol.XIAHUAXIAN_REG);
			int random = random(last);
			for(int i=0;i<newprobs.size();i++){
				int value= newprobs.get(i);
//				String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
//				if(Integer.parseInt(split2[1])>random){
				if(value>random){
					return i;
				}
			}
		}catch (Exception e) {
			logger.error("计算机率错误"+probs.toString(),e);
		}
		return -1;
	}
	
	public static void main(String args[]){
	}

}


