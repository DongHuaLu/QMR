package com.game.protect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 错误通知
	 */
	public class ResPointToClientMessage extends Message {
	
		//1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 164102;
		}
		
		/**
		 * get 1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}