package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 需要在婚宴改变外观的玩家ID
	 */
	public class ResWeddingExteriorToClientMessage extends Message {
	
		//需要改变外观的角色列表
		private var _roleslist: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//需要改变外观的角色列表
			writeShort(_roleslist.length);
			for (i = 0; i < _roleslist.length; i++) {
				writeLong(_roleslist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//需要改变外观的角色列表
			var roleslist_length : int = readShort();
			for (i = 0; i < roleslist_length; i++) {
				_roleslist[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163119;
		}
		
		/**
		 * get 需要改变外观的角色列表
		 * @return 
		 */
		public function get roleslist(): Vector.<long>{
			return _roleslist;
		}
		
		/**
		 * set 需要改变外观的角色列表
		 */
		public function set roleslist(value: Vector.<long>): void{
			this._roleslist = value;
		}
		
	}
}