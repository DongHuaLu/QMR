package com.game.signwage.message{
	import com.game.signwage.bean.SignInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 签到消息
	 */
	public class ResSignWageInfoMessage extends Message {
	
		//签到消息
		private var _signInfo: SignInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//签到消息
			writeBean(_signInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//签到消息
			_signInfo = readBean(SignInfo) as SignInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 152101;
		}
		
		/**
		 * get 签到消息
		 * @return 
		 */
		public function get signInfo(): SignInfo{
			return _signInfo;
		}
		
		/**
		 * set 签到消息
		 */
		public function set signInfo(value: SignInfo): void{
			this._signInfo = value;
		}
		
	}
}