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
	 * 发送单独帮会信息给客户端
	 */
	public class ResGuildAloneGuildInfoToClientMessage extends Message {
	
		//通知类型 0 创建 1 添加或更新 2 删除 等
		private var _notifyType: int;
		
		//单独帮会信息
		private var _guildInfo: GuildInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//通知类型 0 创建 1 添加或更新 2 删除 等
			writeByte(_notifyType);
			//单独帮会信息
			writeBean(_guildInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//通知类型 0 创建 1 添加或更新 2 删除 等
			_notifyType = readByte();
			//单独帮会信息
			_guildInfo = readBean(GuildInfo) as GuildInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121390;
		}
		
		/**
		 * get 通知类型 0 创建 1 添加或更新 2 删除 等
		 * @return 
		 */
		public function get notifyType(): int{
			return _notifyType;
		}
		
		/**
		 * set 通知类型 0 创建 1 添加或更新 2 删除 等
		 */
		public function set notifyType(value: int): void{
			this._notifyType = value;
		}
		
		/**
		 * get 单独帮会信息
		 * @return 
		 */
		public function get guildInfo(): GuildInfo{
			return _guildInfo;
		}
		
		/**
		 * set 单独帮会信息
		 */
		public function set guildInfo(value: GuildInfo): void{
			this._guildInfo = value;
		}
		
	}
}