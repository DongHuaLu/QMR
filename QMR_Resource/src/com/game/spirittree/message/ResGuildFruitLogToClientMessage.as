package com.game.spirittree.message{
	import com.game.spirittree.bean.GuildFruitLog;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 帮会神树操作日志
	 */
	public class ResGuildFruitLogToClientMessage extends Message {
	
		//帮会神树操作日志列表
		private var _guildfruitlog: Vector.<GuildFruitLog> = new Vector.<GuildFruitLog>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//帮会神树操作日志列表
			writeShort(_guildfruitlog.length);
			for (i = 0; i < _guildfruitlog.length; i++) {
				writeBean(_guildfruitlog[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//帮会神树操作日志列表
			var guildfruitlog_length : int = readShort();
			for (i = 0; i < guildfruitlog_length; i++) {
				_guildfruitlog[i] = readBean(GuildFruitLog) as GuildFruitLog;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133107;
		}
		
		/**
		 * get 帮会神树操作日志列表
		 * @return 
		 */
		public function get guildfruitlog(): Vector.<GuildFruitLog>{
			return _guildfruitlog;
		}
		
		/**
		 * set 帮会神树操作日志列表
		 */
		public function set guildfruitlog(value: Vector.<GuildFruitLog>): void{
			this._guildfruitlog = value;
		}
		
	}
}