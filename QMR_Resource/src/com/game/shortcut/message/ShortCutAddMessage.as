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
	 * 快捷增加
	 */
	public class ShortCutAddMessage extends Message {
	
		//快捷信息
		private var _shortcut: ShortCutInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//快捷信息
			writeBean(_shortcut);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//快捷信息
			_shortcut = readBean(ShortCutInfo) as ShortCutInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 108102;
		}
		
		/**
		 * get 快捷信息
		 * @return 
		 */
		public function get shortcut(): ShortCutInfo{
			return _shortcut;
		}
		
		/**
		 * set 快捷信息
		 */
		public function set shortcut(value: ShortCutInfo): void{
			this._shortcut = value;
		}
		
	}
}