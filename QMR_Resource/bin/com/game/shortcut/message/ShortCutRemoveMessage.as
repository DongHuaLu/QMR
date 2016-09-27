package com.game.shortcut.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 快捷删除
	 */
	public class ShortCutRemoveMessage extends Message {
	
		//快捷位置
		private var _shortcutGrid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//快捷位置
			writeInt(_shortcutGrid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//快捷位置
			_shortcutGrid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 108103;
		}
		
		/**
		 * get 快捷位置
		 * @return 
		 */
		public function get shortcutGrid(): int{
			return _shortcutGrid;
		}
		
		/**
		 * set 快捷位置
		 */
		public function set shortcutGrid(value: int): void{
			this._shortcutGrid = value;
		}
		
	}
}