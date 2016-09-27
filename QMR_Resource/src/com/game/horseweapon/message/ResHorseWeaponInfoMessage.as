package com.game.horseweapon.message{
	import com.game.utils.long;
	import com.game.horseweapon.bean.HorseWeaponInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回客户端坐骑信息
	 */
	public class ResHorseWeaponInfoMessage extends Message {
	
		//骑乘兵器使用者ID
		private var _playerid: long;
		
		//坐骑信息
		private var _info: HorseWeaponInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//骑乘兵器使用者ID
			writeLong(_playerid);
			//坐骑信息
			writeBean(_info);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//骑乘兵器使用者ID
			_playerid = readLong();
			//坐骑信息
			_info = readBean(HorseWeaponInfo) as HorseWeaponInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 155101;
		}
		
		/**
		 * get 骑乘兵器使用者ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 骑乘兵器使用者ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 坐骑信息
		 * @return 
		 */
		public function get info(): HorseWeaponInfo{
			return _info;
		}
		
		/**
		 * set 坐骑信息
		 */
		public function set info(value: HorseWeaponInfo): void{
			this._info = value;
		}
		
	}
}