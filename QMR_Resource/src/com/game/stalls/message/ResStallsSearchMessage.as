package com.game.stalls.message{
	import com.game.stalls.bean.StallsBriefList;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摊位搜索结果
	 */
	public class ResStallsSearchMessage extends Message {
	
		//符合条件的摊位简要信息
		private var _stallsbrieflist: StallsBriefList;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//符合条件的摊位简要信息
			writeBean(_stallsbrieflist);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//符合条件的摊位简要信息
			_stallsbrieflist = readBean(StallsBriefList) as StallsBriefList;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123108;
		}
		
		/**
		 * get 符合条件的摊位简要信息
		 * @return 
		 */
		public function get stallsbrieflist(): StallsBriefList{
			return _stallsbrieflist;
		}
		
		/**
		 * set 符合条件的摊位简要信息
		 */
		public function set stallsbrieflist(value: StallsBriefList): void{
			this._stallsbrieflist = value;
		}
		
	}
}