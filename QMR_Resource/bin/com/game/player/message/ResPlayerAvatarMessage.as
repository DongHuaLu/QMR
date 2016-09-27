package com.game.player.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 同步玩家头像改变
	 */
	public class ResPlayerAvatarMessage extends Message {
	
		//玩家id
		private var _playerid: long;
		
		//头像模板id
		private var _avatarid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家id
			writeLong(_playerid);
			//头像模板id
			writeInt(_avatarid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家id
			_playerid = readLong();
			//头像模板id
			_avatarid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103124;
		}
		
		/**
		 * get 玩家id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 头像模板id
		 * @return 
		 */
		public function get avatarid(): int{
			return _avatarid;
		}
		
		/**
		 * set 头像模板id
		 */
		public function set avatarid(value: int): void{
			this._avatarid = value;
		}
		
	}
}