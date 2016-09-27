package com.game.shortcut.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 删除快捷
	 */
	public class RemoveShortCutMessage extends Message {
	
		//快捷Id
		private var _shortcutId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//快捷Id
			writeLong(_shortcutId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//快捷Id
			_shortcutId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 108202;
		}
		
		/**
		 * get 快捷Id
		 * @return 
		 */
		public function get shortcutId(): long{
			return _shortcutId;
		}
		
		/**
		 * set 快捷Id
		 */
		public function set shortcutId(value: long): void{
			this._shortcutId = value;
		}
		
	}
}