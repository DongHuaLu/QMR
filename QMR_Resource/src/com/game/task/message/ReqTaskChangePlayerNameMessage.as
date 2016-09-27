package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 主线任务修改角色名称
	 */
	public class ReqTaskChangePlayerNameMessage extends Message {
	
		//角色名称
		private var _playerName: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色名称
			writeString(_playerName);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色名称
			_playerName = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120217;
		}
		
		/**
		 * get 角色名称
		 * @return 
		 */
		public function get playerName(): String{
			return _playerName;
		}
		
		/**
		 * set 角色名称
		 */
		public function set playerName(value: String): void{
			this._playerName = value;
		}
		
	}
}