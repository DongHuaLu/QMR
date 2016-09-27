package com.game.guild.message{
	import com.game.guild.bean.GuildInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取帮会列表返回
	 */
	public class ResGuildGetGuildListToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//所有帮会列表
		private var _guildList: Vector.<GuildInfo> = new Vector.<GuildInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_btErrorCode);
			//所有帮会列表
			writeShort(_guildList.length);
			for (i = 0; i < _guildList.length; i++) {
				writeBean(_guildList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//错误代码
			_btErrorCode = readByte();
			//所有帮会列表
			var guildList_length : int = readShort();
			for (i = 0; i < guildList_length; i++) {
				_guildList[i] = readBean(GuildInfo) as GuildInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121103;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get btErrorCode(): int{
			return _btErrorCode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set btErrorCode(value: int): void{
			this._btErrorCode = value;
		}
		
		/**
		 * get 所有帮会列表
		 * @return 
		 */
		public function get guildList(): Vector.<GuildInfo>{
			return _guildList;
		}
		
		/**
		 * set 所有帮会列表
		 */
		public function set guildList(value: Vector.<GuildInfo>): void{
			this._guildList = value;
		}
		
	}
}