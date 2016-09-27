package com.game.guild.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 帮主修改自动同意申请加入设置
	 */
	public class ReqGuildAutoGuildArgeeAddToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//自动同意加入帮会的申请
		private var _autoGuildAgreeAdd: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//自动同意加入帮会的申请
			writeByte(_autoGuildAgreeAdd);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会Id
			_guildId = readLong();
			//自动同意加入帮会的申请
			_autoGuildAgreeAdd = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121212;
		}
		
		/**
		 * get 帮会Id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会Id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 自动同意加入帮会的申请
		 * @return 
		 */
		public function get autoGuildAgreeAdd(): int{
			return _autoGuildAgreeAdd;
		}
		
		/**
		 * set 自动同意加入帮会的申请
		 */
		public function set autoGuildAgreeAdd(value: int): void{
			this._autoGuildAgreeAdd = value;
		}
		
	}
}