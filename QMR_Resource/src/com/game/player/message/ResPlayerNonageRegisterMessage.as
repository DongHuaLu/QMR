package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家防沉迷注册返回
	 */
	public class ResPlayerNonageRegisterMessage extends Message {
	
		//玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
		private var _result: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
			writeByte(_result);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
			_result = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103123;
		}
		
		/**
		 * get 玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
		 */
		public function set result(value: int): void{
			this._result = value;
		}
		
	}
}