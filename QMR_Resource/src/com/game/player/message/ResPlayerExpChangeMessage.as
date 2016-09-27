package com.game.player.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 经验变化
	 */
	public class ResPlayerExpChangeMessage extends Message {
	
		//当前经验
		private var _currentExp: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前经验
			writeLong(_currentExp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前经验
			_currentExp = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103103;
		}
		
		/**
		 * get 当前经验
		 * @return 
		 */
		public function get currentExp(): long{
			return _currentExp;
		}
		
		/**
		 * set 当前经验
		 */
		public function set currentExp(value: long): void{
			this._currentExp = value;
		}
		
	}
}