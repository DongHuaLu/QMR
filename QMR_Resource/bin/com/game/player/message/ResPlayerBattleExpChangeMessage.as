package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 战场经验变化
	 */
	public class ResPlayerBattleExpChangeMessage extends Message {
	
		//当前战场经验
		private var _currentBattleExp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前战场经验
			writeInt(_currentBattleExp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前战场经验
			_currentBattleExp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103114;
		}
		
		/**
		 * get 当前战场经验
		 * @return 
		 */
		public function get currentBattleExp(): int{
			return _currentBattleExp;
		}
		
		/**
		 * set 当前战场经验
		 */
		public function set currentBattleExp(value: int): void{
			this._currentBattleExp = value;
		}
		
	}
}