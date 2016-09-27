package com.game.npc.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求npc脚本服务
	 */
	public class ReqServiceMessage extends Message {
	
		//npcId
		private var _npcId: long;
		
		//服务参数
		private var _serviceParameter: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//npcId
			writeLong(_npcId);
			//服务参数
			writeString(_serviceParameter);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//npcId
			_npcId = readLong();
			//服务参数
			_serviceParameter = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 140202;
		}
		
		/**
		 * get npcId
		 * @return 
		 */
		public function get npcId(): long{
			return _npcId;
		}
		
		/**
		 * set npcId
		 */
		public function set npcId(value: long): void{
			this._npcId = value;
		}
		
		/**
		 * get 服务参数
		 * @return 
		 */
		public function get serviceParameter(): String{
			return _serviceParameter;
		}
		
		/**
		 * set 服务参数
		 */
		public function set serviceParameter(value: String): void{
			this._serviceParameter = value;
		}
		
	}
}