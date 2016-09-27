package com.game.map.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围消失角色
	 */
	public class ResRoundPlayerDisappearMessage extends Message {
	
		//消失角色列表
		private var _playerIds: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消失角色列表
			writeShort(_playerIds.length);
			for (i = 0; i < _playerIds.length; i++) {
				writeLong(_playerIds[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消失角色列表
			var playerIds_length : int = readShort();
			for (i = 0; i < playerIds_length; i++) {
				_playerIds[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101105;
		}
		
		/**
		 * get 消失角色列表
		 * @return 
		 */
		public function get playerIds(): Vector.<long>{
			return _playerIds;
		}
		
		/**
		 * set 消失角色列表
		 */
		public function set playerIds(value: Vector.<long>): void{
			this._playerIds = value;
		}
		
	}
}