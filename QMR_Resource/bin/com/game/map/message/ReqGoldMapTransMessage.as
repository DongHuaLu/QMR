package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 世界地图元宝传送
	 */
	public class ReqGoldMapTransMessage extends Message {
	
		//地图序号
		private var _mapId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//地图序号
			writeInt(_mapId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//地图序号
			_mapId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101210;
		}
		
		/**
		 * get 地图序号
		 * @return 
		 */
		public function get mapId(): int{
			return _mapId;
		}
		
		/**
		 * set 地图序号
		 */
		public function set mapId(value: int): void{
			this._mapId = value;
		}
		
	}
}