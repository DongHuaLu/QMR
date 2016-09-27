package com.game.horseweapon.message{
	import com.game.utils.long;
	import com.game.horseweapon.bean.OthersHorseWeaponInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回他人骑乘兵器消息
	 */
	public class OthersHorseWeaponInfoMessage extends Message {
	
		//骑乘兵器使用者ID
		private var _playerid: long;
		
		//骑乘兵器信息
		private var _info: OthersHorseWeaponInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//骑乘兵器使用者ID
			writeLong(_playerid);
			//骑乘兵器信息
			writeBean(_info);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//骑乘兵器使用者ID
			_playerid = readLong();
			//骑乘兵器信息
			_info = readBean(OthersHorseWeaponInfo) as OthersHorseWeaponInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 155105;
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
		 * get 骑乘兵器信息
		 * @return 
		 */
		public function get info(): OthersHorseWeaponInfo{
			return _info;
		}
		
		/**
		 * set 骑乘兵器信息
		 */
		public function set info(value: OthersHorseWeaponInfo): void{
			this._info = value;
		}
		
	}
}