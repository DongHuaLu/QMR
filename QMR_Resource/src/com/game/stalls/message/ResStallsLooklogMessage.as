package com.game.stalls.message{
	import com.game.stalls.bean.StallsLogInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 个人摊位交易日志
	 */
	public class ResStallsLooklogMessage extends Message {
	
		//摊位交易日志列表
		private var _stallslogInfo: StallsLogInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//摊位交易日志列表
			writeBean(_stallslogInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//摊位交易日志列表
			_stallslogInfo = readBean(StallsLogInfo) as StallsLogInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123109;
		}
		
		/**
		 * get 摊位交易日志列表
		 * @return 
		 */
		public function get stallslogInfo(): StallsLogInfo{
			return _stallslogInfo;
		}
		
		/**
		 * set 摊位交易日志列表
		 */
		public function set stallslogInfo(value: StallsLogInfo): void{
			this._stallslogInfo = value;
		}
		
	}
}