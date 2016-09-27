package com.game.signwage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送累计签到次数
	 */
	public class RessignnumToClientMessage extends Message {
	
		//累计签到次数
		private var _signnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//累计签到次数
			writeInt(_signnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//累计签到次数
			_signnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 152104;
		}
		
		/**
		 * get 累计签到次数
		 * @return 
		 */
		public function get signnum(): int{
			return _signnum;
		}
		
		/**
		 * set 累计签到次数
		 */
		public function set signnum(value: int): void{
			this._signnum = value;
		}
		
	}
}