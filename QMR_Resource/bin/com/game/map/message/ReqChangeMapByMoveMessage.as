package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 切换地图
	 */
	public class ReqChangeMapByMoveMessage extends Message {
	
		//切换线路
		private var _line: int;
		
		//传送点Id
		private var _tranId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//切换线路
			writeInt(_line);
			//传送点Id
			writeInt(_tranId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//切换线路
			_line = readInt();
			//传送点Id
			_tranId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101206;
		}
		
		/**
		 * get 切换线路
		 * @return 
		 */
		public function get line(): int{
			return _line;
		}
		
		/**
		 * set 切换线路
		 */
		public function set line(value: int): void{
			this._line = value;
		}
		
		/**
		 * get 传送点Id
		 * @return 
		 */
		public function get tranId(): int{
			return _tranId;
		}
		
		/**
		 * set 传送点Id
		 */
		public function set tranId(value: int): void{
			this._tranId = value;
		}
		
	}
}