package com.game.card.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求签证CDKEY返回
	 */
	public class ResCardToClientMessage extends Message {
	
		//错误代码
		private var _errorcode: int;
		
		//平台id
		private var _agid: int;
		
		//礼包类型
		private var _type: int;
		
		//礼包编号
		private var _giftid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_errorcode);
			//平台id
			writeInt(_agid);
			//礼包类型
			writeInt(_type);
			//礼包编号
			writeInt(_giftid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_errorcode = readByte();
			//平台id
			_agid = readInt();
			//礼包类型
			_type = readInt();
			//礼包编号
			_giftid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 137151;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get errorcode(): int{
			return _errorcode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set errorcode(value: int): void{
			this._errorcode = value;
		}
		
		/**
		 * get 平台id
		 * @return 
		 */
		public function get agid(): int{
			return _agid;
		}
		
		/**
		 * set 平台id
		 */
		public function set agid(value: int): void{
			this._agid = value;
		}
		
		/**
		 * get 礼包类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 礼包类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 礼包编号
		 * @return 
		 */
		public function get giftid(): int{
			return _giftid;
		}
		
		/**
		 * set 礼包编号
		 */
		public function set giftid(value: int): void{
			this._giftid = value;
		}
		
	}
}