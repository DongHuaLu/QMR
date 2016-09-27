package com.game.shortcut.message{
	import com.game.shortcut.bean.ShortCutInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 快捷信息列表
	 */
	public class ShortCutInfosMessage extends Message {
	
		//快捷信息列表
		private var _shortcuts: Vector.<ShortCutInfo> = new Vector.<ShortCutInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//快捷信息列表
			writeShort(_shortcuts.length);
			for (i = 0; i < _shortcuts.length; i++) {
				writeBean(_shortcuts[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//快捷信息列表
			var shortcuts_length : int = readShort();
			for (i = 0; i < shortcuts_length; i++) {
				_shortcuts[i] = readBean(ShortCutInfo) as ShortCutInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 108101;
		}
		
		/**
		 * get 快捷信息列表
		 * @return 
		 */
		public function get shortcuts(): Vector.<ShortCutInfo>{
			return _shortcuts;
		}
		
		/**
		 * set 快捷信息列表
		 */
		public function set shortcuts(value: Vector.<ShortCutInfo>): void{
			this._shortcuts = value;
		}
		
	}
}