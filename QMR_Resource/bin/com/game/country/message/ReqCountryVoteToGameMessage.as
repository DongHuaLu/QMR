package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家选择BUFF投票
	 */
	public class ReqCountryVoteToGameMessage extends Message {
	
		//选择的BUFFID
		private var _buffmodelid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//选择的BUFFID
			writeInt(_buffmodelid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//选择的BUFFID
			_buffmodelid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146208;
		}
		
		/**
		 * get 选择的BUFFID
		 * @return 
		 */
		public function get buffmodelid(): int{
			return _buffmodelid;
		}
		
		/**
		 * set 选择的BUFFID
		 */
		public function set buffmodelid(value: int): void{
			this._buffmodelid = value;
		}
		
	}
}