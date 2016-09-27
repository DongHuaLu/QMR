package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 配偶切换地图
	 */
	public class ResSpouseSwitchmapToClientMessage extends Message {
	
		//地图ID
		private var _mapid: long;
		
		//线路
		private var _line: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//地图ID
			writeLong(_mapid);
			//线路
			writeInt(_line);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//地图ID
			_mapid = readLong();
			//线路
			_line = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163124;
		}
		
		/**
		 * get 地图ID
		 * @return 
		 */
		public function get mapid(): long{
			return _mapid;
		}
		
		/**
		 * set 地图ID
		 */
		public function set mapid(value: long): void{
			this._mapid = value;
		}
		
		/**
		 * get 线路
		 * @return 
		 */
		public function get line(): int{
			return _line;
		}
		
		/**
		 * set 线路
		 */
		public function set line(value: int): void{
			this._line = value;
		}
		
	}
}