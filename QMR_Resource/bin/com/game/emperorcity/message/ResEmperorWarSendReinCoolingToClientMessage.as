package com.game.emperorcity.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送援军冷却时间
	 */
	public class ResEmperorWarSendReinCoolingToClientMessage extends Message {
	
		//攻方冷却时间
		private var _attacktime: int;
		
		//守方冷却时间
		private var _defenstime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//攻方冷却时间
			writeInt(_attacktime);
			//守方冷却时间
			writeInt(_defenstime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//攻方冷却时间
			_attacktime = readInt();
			//守方冷却时间
			_defenstime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169108;
		}
		
		/**
		 * get 攻方冷却时间
		 * @return 
		 */
		public function get attacktime(): int{
			return _attacktime;
		}
		
		/**
		 * set 攻方冷却时间
		 */
		public function set attacktime(value: int): void{
			this._attacktime = value;
		}
		
		/**
		 * get 守方冷却时间
		 * @return 
		 */
		public function get defenstime(): int{
			return _defenstime;
		}
		
		/**
		 * set 守方冷却时间
		 */
		public function set defenstime(value: int): void{
			this._defenstime = value;
		}
		
	}
}