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
	 * 响应打开摊位面板
	 */
	public class ResStallsOpenUpMessage extends Message {
	
		//所有摊位简要信息
		private var _stallsbrieflist: StallsBriefList;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//所有摊位简要信息
			writeBean(_stallsbrieflist);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//所有摊位简要信息
			_stallsbrieflist = readBean(StallsBriefList) as StallsBriefList;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123101;
		}
		
		/**
		 * get 所有摊位简要信息
		 * @return 
		 */
		public function get stallsbrieflist(): StallsBriefList{
			return _stallsbrieflist;
		}
		
		/**
		 * set 所有摊位简要信息
		 */
		public function set stallsbrieflist(value: StallsBriefList): void{
			this._stallsbrieflist = value;
		}
		
	}
}