package com.game.country.message{
	import com.game.country.bean.PlayerDamageInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 炮弹击中人物信息
	 */
	public class ResCountryWarCarDamageToClientMessage extends Message {
	
		//攻击坐标X
		private var _x: int;
		
		//攻击坐标Y
		private var _y: int;
		
		//地图人物信息
		private var _damageinfo: Vector.<PlayerDamageInfo> = new Vector.<PlayerDamageInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//攻击坐标X
			writeShort(_x);
			//攻击坐标Y
			writeShort(_y);
			//地图人物信息
			writeShort(_damageinfo.length);
			for (i = 0; i < _damageinfo.length; i++) {
				writeBean(_damageinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//攻击坐标X
			_x = readShort();
			//攻击坐标Y
			_y = readShort();
			//地图人物信息
			var damageinfo_length : int = readShort();
			for (i = 0; i < damageinfo_length; i++) {
				_damageinfo[i] = readBean(PlayerDamageInfo) as PlayerDamageInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146112;
		}
		
		/**
		 * get 攻击坐标X
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 攻击坐标X
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 攻击坐标Y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 攻击坐标Y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
		/**
		 * get 地图人物信息
		 * @return 
		 */
		public function get damageinfo(): Vector.<PlayerDamageInfo>{
			return _damageinfo;
		}
		
		/**
		 * set 地图人物信息
		 */
		public function set damageinfo(value: Vector.<PlayerDamageInfo>): void{
			this._damageinfo = value;
		}
		
	}
}