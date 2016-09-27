package com.game.classicbattle.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 首次通关弹出奖励面板
	 */
	public class ResClassicBattleFirstToClientMessage extends Message {
	
		//首次通关奖励列表
		private var _firstrewards: String;
		
		//当前层数
		private var _currlayers: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//首次通关奖励列表
			writeString(_firstrewards);
			//当前层数
			writeInt(_currlayers);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//首次通关奖励列表
			_firstrewards = readString();
			//当前层数
			_currlayers = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 165104;
		}
		
		/**
		 * get 首次通关奖励列表
		 * @return 
		 */
		public function get firstrewards(): String{
			return _firstrewards;
		}
		
		/**
		 * set 首次通关奖励列表
		 */
		public function set firstrewards(value: String): void{
			this._firstrewards = value;
		}
		
		/**
		 * get 当前层数
		 * @return 
		 */
		public function get currlayers(): int{
			return _currlayers;
		}
		
		/**
		 * set 当前层数
		 */
		public function set currlayers(value: int): void{
			this._currlayers = value;
		}
		
	}
}