package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 多人副本进入方式
	 */
	public class ReqZoneTeamEnterToGameMessage extends Message {
	
		//进入方式，0单人，1组队，2报名,3队伍报名
		private var _entertype: int;
		
		//副本ID
		private var _zoneid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//进入方式，0单人，1组队，2报名,3队伍报名
			writeByte(_entertype);
			//副本ID
			writeInt(_zoneid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//进入方式，0单人，1组队，2报名,3队伍报名
			_entertype = readByte();
			//副本ID
			_zoneid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128211;
		}
		
		/**
		 * get 进入方式，0单人，1组队，2报名,3队伍报名
		 * @return 
		 */
		public function get entertype(): int{
			return _entertype;
		}
		
		/**
		 * set 进入方式，0单人，1组队，2报名,3队伍报名
		 */
		public function set entertype(value: int): void{
			this._entertype = value;
		}
		
		/**
		 * get 副本ID
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本ID
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
	}
}