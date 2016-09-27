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
	 * 请求服务器npc服务列表
	 */
	public class ReqNpcServicesMessage extends Message {
	
		//npcId
		private var _npcId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//npcId
			writeLong(_npcId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//npcId
			_npcId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 140201;
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
		
	}
}