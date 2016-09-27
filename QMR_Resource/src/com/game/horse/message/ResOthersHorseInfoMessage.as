package com.game.horse.message{
	import com.game.utils.long;
	import com.game.horse.bean.OthersHorseInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回他人坐骑消息
	 */
	public class ResOthersHorseInfoMessage extends Message {
	
		//坐骑使用者ID
		private var _playerid: long;
		
		//坐骑信息
		private var _info: OthersHorseInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//坐骑使用者ID
			writeLong(_playerid);
			//坐骑信息
			writeBean(_info);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//坐骑使用者ID
			_playerid = readLong();
			//坐骑信息
			_info = readBean(OthersHorseInfo) as OthersHorseInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126112;
		}
		
		/**
		 * get 坐骑使用者ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 坐骑使用者ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 坐骑信息
		 * @return 
		 */
		public function get info(): OthersHorseInfo{
			return _info;
		}
		
		/**
		 * set 坐骑信息
		 */
		public function set info(value: OthersHorseInfo): void{
			this._info = value;
		}
		
	}
}