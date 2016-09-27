package com.game.batter.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 上线发送连斩次数
	 */
	public class ResMomentKillToClientMessage extends Message {
	
		//连斩攻击
		private var _evencutatk: int;
		
		//连击攻击
		private var _batteratk: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//连斩攻击
			writeInt(_evencutatk);
			//连击攻击
			writeInt(_batteratk);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//连斩攻击
			_evencutatk = readInt();
			//连击攻击
			_batteratk = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 141102;
		}
		
		/**
		 * get 连斩攻击
		 * @return 
		 */
		public function get evencutatk(): int{
			return _evencutatk;
		}
		
		/**
		 * set 连斩攻击
		 */
		public function set evencutatk(value: int): void{
			this._evencutatk = value;
		}
		
		/**
		 * get 连击攻击
		 * @return 
		 */
		public function get batteratk(): int{
			return _batteratk;
		}
		
		/**
		 * set 连击攻击
		 */
		public function set batteratk(value: int): void{
			this._batteratk = value;
		}
		
	}
}