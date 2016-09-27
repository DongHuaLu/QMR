package com.game.signwage.message{
	import com.game.signwage.bean.WageInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 工资消息
	 */
	public class ResSignWagetoWageInfoMessage extends Message {
	
		//工资消息
		private var _wageInfo: WageInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//工资消息
			writeBean(_wageInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//工资消息
			_wageInfo = readBean(WageInfo) as WageInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 152102;
		}
		
		/**
		 * get 工资消息
		 * @return 
		 */
		public function get wageInfo(): WageInfo{
			return _wageInfo;
		}
		
		/**
		 * set 工资消息
		 */
		public function set wageInfo(value: WageInfo): void{
			this._wageInfo = value;
		}
		
	}
}