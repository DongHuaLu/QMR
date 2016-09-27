package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 组队副本开放时，国家公告
	 */
	public class ResZoneTeamOpenBullToClientMessage extends Message {
	
		//副本模版编号
		private var _zonemodelid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//副本模版编号
			writeInt(_zonemodelid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本模版编号
			_zonemodelid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128119;
		}
		
		/**
		 * get 副本模版编号
		 * @return 
		 */
		public function get zonemodelid(): int{
			return _zonemodelid;
		}
		
		/**
		 * set 副本模版编号
		 */
		public function set zonemodelid(value: int): void{
			this._zonemodelid = value;
		}
		
	}
}