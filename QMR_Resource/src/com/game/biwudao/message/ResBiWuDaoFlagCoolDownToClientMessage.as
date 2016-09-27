package com.game.biwudao.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 夺旗剩余冷却时间
	 */
	public class ResBiWuDaoFlagCoolDownToClientMessage extends Message {
	
		//夺旗剩余冷却时间
		private var _flagcooldown: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//夺旗剩余冷却时间
			writeInt(_flagcooldown);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//夺旗剩余冷却时间
			_flagcooldown = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157105;
		}
		
		/**
		 * get 夺旗剩余冷却时间
		 * @return 
		 */
		public function get flagcooldown(): int{
			return _flagcooldown;
		}
		
		/**
		 * set 夺旗剩余冷却时间
		 */
		public function set flagcooldown(value: int): void{
			this._flagcooldown = value;
		}
		
	}
}