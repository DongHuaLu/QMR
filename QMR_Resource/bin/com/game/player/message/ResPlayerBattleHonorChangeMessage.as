package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 战场荣誉变化
	 */
	public class ResPlayerBattleHonorChangeMessage extends Message {
	
		//当前战场荣誉
		private var _currentBattleHonor: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前战场荣誉
			writeInt(_currentBattleHonor);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前战场荣誉
			_currentBattleHonor = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103137;
		}
		
		/**
		 * get 当前战场荣誉
		 * @return 
		 */
		public function get currentBattleHonor(): int{
			return _currentBattleHonor;
		}
		
		/**
		 * set 当前战场荣誉
		 */
		public function set currentBattleHonor(value: int): void{
			this._currentBattleHonor = value;
		}
		
	}
}