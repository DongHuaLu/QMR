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
	 * 删除外交关系
	 */
	public class ReqGuildDeleteDiplomaticToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//外交关系类型
		private var _diplomaticType: int;
		
		//被操作帮会Id
		private var _otherGuildId: long;
		
		//是否同意
		private var _argee: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//外交关系类型
			writeByte(_diplomaticType);
			//被操作帮会Id
			writeLong(_otherGuildId);
			//是否同意
			writeByte(_argee);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会Id
			_guildId = readLong();
			//外交关系类型
			_diplomaticType = readByte();
			//被操作帮会Id
			_otherGuildId = readLong();
			//是否同意
			_argee = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121219;
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
		 * get 外交关系类型
		 * @return 
		 */
		public function get diplomaticType(): int{
			return _diplomaticType;
		}
		
		/**
		 * set 外交关系类型
		 */
		public function set diplomaticType(value: int): void{
			this._diplomaticType = value;
		}
		
		/**
		 * get 被操作帮会Id
		 * @return 
		 */
		public function get otherGuildId(): long{
			return _otherGuildId;
		}
		
		/**
		 * set 被操作帮会Id
		 */
		public function set otherGuildId(value: long): void{
			this._otherGuildId = value;
		}
		
		/**
		 * get 是否同意
		 * @return 
		 */
		public function get argee(): int{
			return _argee;
		}
		
		/**
		 * set 是否同意
		 */
		public function set argee(value: int): void{
			this._argee = value;
		}
		
	}
}