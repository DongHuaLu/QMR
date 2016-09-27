package com.game.guild.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家造型信息
	 */
	public class PlayerModeInfo extends Bean {
	
		//玩家id
		private var _userId: long;
		
		//玩家头像icon
		private var _icon: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家id
			writeLong(_userId);
			//玩家头像icon
			writeInt(_icon);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家id
			_userId = readLong();
			//玩家头像icon
			_icon = readInt();
			return true;
		}
		
		/**
		 * get 玩家id
		 * @return 
		 */
		public function get userId(): long{
			return _userId;
		}
		
		/**
		 * set 玩家id
		 */
		public function set userId(value: long): void{
			this._userId = value;
		}
		
		/**
		 * get 玩家头像icon
		 * @return 
		 */
		public function get icon(): int{
			return _icon;
		}
		
		/**
		 * set 玩家头像icon
		 */
		public function set icon(value: int): void{
			this._icon = value;
		}
		
	}
}