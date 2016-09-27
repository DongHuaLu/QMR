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
	 * 外交关系信息
	 */
	public class DiplomaticInfo extends Bean {
	
		//帮会id
		private var _guildId: long;
		
		//外交建立时间
		private var _diplomaticTime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会id
			writeLong(_guildId);
			//外交建立时间
			writeInt(_diplomaticTime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会id
			_guildId = readLong();
			//外交建立时间
			_diplomaticTime = readInt();
			return true;
		}
		
		/**
		 * get 帮会id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 外交建立时间
		 * @return 
		 */
		public function get diplomaticTime(): int{
			return _diplomaticTime;
		}
		
		/**
		 * set 外交建立时间
		 */
		public function set diplomaticTime(value: int): void{
			this._diplomaticTime = value;
		}
		
	}
}