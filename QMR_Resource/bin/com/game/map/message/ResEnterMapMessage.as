package com.game.map.message{
	import com.game.structs.Position;
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
	public class ResEnterMapMessage extends Message {
	
		//所在线
		private var _line: int;
		
		//坐标
		private var _pos: com.game.structs.Position;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//所在线
			writeInt(_line);
			//坐标
			writeBean(_pos);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//所在线
			_line = readInt();
			//坐标
			_pos = readBean(com.game.structs.Position) as com.game.structs.Position;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101120;
		}
		
		/**
		 * get 所在线
		 * @return 
		 */
		public function get line(): int{
			return _line;
		}
		
		/**
		 * set 所在线
		 */
		public function set line(value: int): void{
			this._line = value;
		}
		
		/**
		 * get 坐标
		 * @return 
		 */
		public function get pos(): com.game.structs.Position{
			return _pos;
		}
		
		/**
		 * set 坐标
		 */
		public function set pos(value: com.game.structs.Position): void{
			this._pos = value;
		}
		
	}
}