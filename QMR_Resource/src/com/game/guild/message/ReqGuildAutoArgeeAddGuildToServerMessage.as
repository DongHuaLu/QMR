package com.game.guild.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家修改自动同意加入帮会设置
	 */
	public class ReqGuildAutoArgeeAddGuildToServerMessage extends Message {
	
		//自动同意参数
		private var _autoArgeeAddGuild: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//自动同意参数
			writeByte(_autoArgeeAddGuild);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//自动同意参数
			_autoArgeeAddGuild = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121202;
		}
		
		/**
		 * get 自动同意参数
		 * @return 
		 */
		public function get autoArgeeAddGuild(): int{
			return _autoArgeeAddGuild;
		}
		
		/**
		 * set 自动同意参数
		 */
		public function set autoArgeeAddGuild(value: int): void{
			this._autoArgeeAddGuild = value;
		}
		
	}
}