package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家防沉迷注册身份证
	 */
	public class ReqPlayerNonageRegisterMessage extends Message {
	
		//名字
		private var _name: String;
		
		//证件号码
		private var _idCard: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//名字
			writeString(_name);
			//证件号码
			writeString(_idCard);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//名字
			_name = readString();
			//证件号码
			_idCard = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103208;
		}
		
		/**
		 * get 名字
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 名字
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 证件号码
		 * @return 
		 */
		public function get idCard(): String{
			return _idCard;
		}
		
		/**
		 * set 证件号码
		 */
		public function set idCard(value: String): void{
			this._idCard = value;
		}
		
	}
}