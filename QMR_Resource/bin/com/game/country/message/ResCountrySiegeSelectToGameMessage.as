package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家选择是否参与攻城
	 */
	public class ResCountrySiegeSelectToGameMessage extends Message {
	
		//是否参与，1参与攻城
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//是否参与，1参与攻城
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//是否参与，1参与攻城
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146201;
		}
		
		/**
		 * get 是否参与，1参与攻城
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 是否参与，1参与攻城
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}