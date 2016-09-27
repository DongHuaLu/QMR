package com.game.classicbattle.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知前端当前层挑战成功
	 */
	public class ResClassicBattleSuccessToClientMessage extends Message {
	
		//BOSS怪物ID
		private var _monmodlid: int;
		
		//当前层数
		private var _currlayers: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//BOSS怪物ID
			writeInt(_monmodlid);
			//当前层数
			writeInt(_currlayers);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//BOSS怪物ID
			_monmodlid = readInt();
			//当前层数
			_currlayers = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 165103;
		}
		
		/**
		 * get BOSS怪物ID
		 * @return 
		 */
		public function get monmodlid(): int{
			return _monmodlid;
		}
		
		/**
		 * set BOSS怪物ID
		 */
		public function set monmodlid(value: int): void{
			this._monmodlid = value;
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