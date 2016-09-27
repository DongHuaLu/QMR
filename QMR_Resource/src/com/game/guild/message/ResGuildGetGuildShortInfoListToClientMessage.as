package com.game.guild.message{
	import com.game.guild.bean.GuildShortInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取帮会简略信息列表返回
	 */
	public class ResGuildGetGuildShortInfoListToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//帮会简略信息列表
		private var _guildShortInfoList: Vector.<GuildShortInfo> = new Vector.<GuildShortInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_btErrorCode);
			//帮会简略信息列表
			writeShort(_guildShortInfoList.length);
			for (i = 0; i < _guildShortInfoList.length; i++) {
				writeBean(_guildShortInfoList[i]);
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
			//帮会简略信息列表
			var guildShortInfoList_length : int = readShort();
			for (i = 0; i < guildShortInfoList_length; i++) {
				_guildShortInfoList[i] = readBean(GuildShortInfo) as GuildShortInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121122;
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
		 * get 帮会简略信息列表
		 * @return 
		 */
		public function get guildShortInfoList(): Vector.<GuildShortInfo>{
			return _guildShortInfoList;
		}
		
		/**
		 * set 帮会简略信息列表
		 */
		public function set guildShortInfoList(value: Vector.<GuildShortInfo>): void{
			this._guildShortInfoList = value;
		}
		
	}
}