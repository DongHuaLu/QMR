package com.game.npc.message{
	import com.game.utils.long;
	import com.game.npc.bean.ServiceInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回服务器npc服务列表
	 */
	public class ResNpcServicesMessage extends Message {
	
		//npcId
		private var _npcId: long;
		
		//npc服务列表集合
		private var _services: Vector.<ServiceInfo> = new Vector.<ServiceInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//npcId
			writeLong(_npcId);
			//npc服务列表集合
			writeShort(_services.length);
			for (i = 0; i < _services.length; i++) {
				writeBean(_services[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//npcId
			_npcId = readLong();
			//npc服务列表集合
			var services_length : int = readShort();
			for (i = 0; i < services_length; i++) {
				_services[i] = readBean(ServiceInfo) as ServiceInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 140101;
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
		 * get npc服务列表集合
		 * @return 
		 */
		public function get services(): Vector.<ServiceInfo>{
			return _services;
		}
		
		/**
		 * set npc服务列表集合
		 */
		public function set services(value: Vector.<ServiceInfo>): void{
			this._services = value;
		}
		
	}
}