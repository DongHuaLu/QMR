package com.game.biwudao.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 比武岛活动剩余时间
	 */
	public class ResBiWuDaoSurplusTimeToClientMessage extends Message {
	
		//活动剩余时间(秒),大于0表示活动进行中
		private var _surplustime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//活动剩余时间(秒),大于0表示活动进行中
			writeInt(_surplustime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//活动剩余时间(秒),大于0表示活动进行中
			_surplustime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157106;
		}
		
		/**
		 * get 活动剩余时间(秒),大于0表示活动进行中
		 * @return 
		 */
		public function get surplustime(): int{
			return _surplustime;
		}
		
		/**
		 * set 活动剩余时间(秒),大于0表示活动进行中
		 */
		public function set surplustime(value: int): void{
			this._surplustime = value;
		}
		
	}
}