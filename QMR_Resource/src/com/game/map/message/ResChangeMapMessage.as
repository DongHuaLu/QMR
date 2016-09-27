package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 切换地图返回
	 */
	public class ResChangeMapMessage extends Message {
	
		//切换地图类型
		private var _type: int;
		
		//地图Id
		private var _mapId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//切换地图类型
			writeByte(_type);
			//地图Id
			writeInt(_mapId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//切换地图类型
			_type = readByte();
			//地图Id
			_mapId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101117;
		}
		
		/**
		 * get 切换地图类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 切换地图类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 地图Id
		 * @return 
		 */
		public function get mapId(): int{
			return _mapId;
		}
		
		/**
		 * set 地图Id
		 */
		public function set mapId(value: int): void{
			this._mapId = value;
		}
		
	}
}