package com.game.ybcard.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 前端发起打开元宝卡
	 */
	public class ReqYBCardToGameMessage extends Message {
	
		//类型，0打开面板，1使用元宝卡，2领取元宝
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型，0打开面板，1使用元宝卡，2领取元宝
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型，0打开面板，1使用元宝卡，2领取元宝
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 139201;
		}
		
		/**
		 * get 类型，0打开面板，1使用元宝卡，2领取元宝
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型，0打开面板，1使用元宝卡，2领取元宝
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}