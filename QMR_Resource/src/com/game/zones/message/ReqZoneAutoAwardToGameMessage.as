package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 战役一键自动领奖励
	 */
	public class ReqZoneAutoAwardToGameMessage extends Message {
	
		//类型，1剧情副本，4七曜战将
		private var _zonetype: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型，1剧情副本，4七曜战将
			writeInt(_zonetype);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型，1剧情副本，4七曜战将
			_zonetype = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128214;
		}
		
		/**
		 * get 类型，1剧情副本，4七曜战将
		 * @return 
		 */
		public function get zonetype(): int{
			return _zonetype;
		}
		
		/**
		 * set 类型，1剧情副本，4七曜战将
		 */
		public function set zonetype(value: int): void{
			this._zonetype = value;
		}
		
	}
}