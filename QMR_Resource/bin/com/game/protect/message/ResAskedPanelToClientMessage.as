package com.game.protect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知前端弹出询问面板
	 */
	public class ResAskedPanelToClientMessage extends Message {
	
		//面板类型：1设置密码前，2输入密码框
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//面板类型：1设置密码前，2输入密码框
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//面板类型：1设置密码前，2输入密码框
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 164101;
		}
		
		/**
		 * get 面板类型：1设置密码前，2输入密码框
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 面板类型：1设置密码前，2输入密码框
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}