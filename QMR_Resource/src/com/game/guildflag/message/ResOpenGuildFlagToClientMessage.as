package com.game.guildflag.message{
	import com.game.guildflag.bean.GuildFlagInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送帮会领地信息
	 */
	public class ResOpenGuildFlagToClientMessage extends Message {
	
		//帮会领地信息列表
		private var _challengeInfo: Vector.<GuildFlagInfo> = new Vector.<GuildFlagInfo>();
		//领地战文字提示
		private var _status: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//帮会领地信息列表
			writeShort(_challengeInfo.length);
			for (i = 0; i < _challengeInfo.length; i++) {
				writeBean(_challengeInfo[i]);
			}
			//领地战文字提示
			writeString(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//帮会领地信息列表
			var challengeInfo_length : int = readShort();
			for (i = 0; i < challengeInfo_length; i++) {
				_challengeInfo[i] = readBean(GuildFlagInfo) as GuildFlagInfo;
			}
			//领地战文字提示
			_status = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 149101;
		}
		
		/**
		 * get 帮会领地信息列表
		 * @return 
		 */
		public function get challengeInfo(): Vector.<GuildFlagInfo>{
			return _challengeInfo;
		}
		
		/**
		 * set 帮会领地信息列表
		 */
		public function set challengeInfo(value: Vector.<GuildFlagInfo>): void{
			this._challengeInfo = value;
		}
		
		/**
		 * get 领地战文字提示
		 * @return 
		 */
		public function get status(): String{
			return _status;
		}
		
		/**
		 * set 领地战文字提示
		 */
		public function set status(value: String): void{
			this._status = value;
		}
		
	}
}