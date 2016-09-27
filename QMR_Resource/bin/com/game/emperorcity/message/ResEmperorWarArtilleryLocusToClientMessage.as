package com.game.emperorcity.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 地图广播炮弹轨迹
	 */
	public class ResEmperorWarArtilleryLocusToClientMessage extends Message {
	
		//炮弹类型
		private var _type: int;
		
		//终点坐标X
		private var _endx: int;
		
		//终点坐标Y
		private var _endy: int;
		
		//开炮玩家ID
		private var _playerid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//炮弹类型
			writeByte(_type);
			//终点坐标X
			writeInt(_endx);
			//终点坐标Y
			writeInt(_endy);
			//开炮玩家ID
			writeLong(_playerid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//炮弹类型
			_type = readByte();
			//终点坐标X
			_endx = readInt();
			//终点坐标Y
			_endy = readInt();
			//开炮玩家ID
			_playerid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169104;
		}
		
		/**
		 * get 炮弹类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 炮弹类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 终点坐标X
		 * @return 
		 */
		public function get endx(): int{
			return _endx;
		}
		
		/**
		 * set 终点坐标X
		 */
		public function set endx(value: int): void{
			this._endx = value;
		}
		
		/**
		 * get 终点坐标Y
		 * @return 
		 */
		public function get endy(): int{
			return _endy;
		}
		
		/**
		 * set 终点坐标Y
		 */
		public function set endy(value: int): void{
			this._endy = value;
		}
		
		/**
		 * get 开炮玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 开炮玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
	}
}