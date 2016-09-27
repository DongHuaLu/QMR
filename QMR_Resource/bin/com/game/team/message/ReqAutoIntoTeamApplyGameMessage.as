package com.game.team.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 自动接受入队申请
	 */
	public class ReqAutoIntoTeamApplyGameMessage extends Message {
	
		//0手动，1自动接受
		private var _autointoteamapply: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0手动，1自动接受
			writeByte(_autointoteamapply);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0手动，1自动接受
			_autointoteamapply = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118210;
		}
		
		/**
		 * get 0手动，1自动接受
		 * @return 
		 */
		public function get autointoteamapply(): int{
			return _autointoteamapply;
		}
		
		/**
		 * set 0手动，1自动接受
		 */
		public function set autointoteamapply(value: int): void{
			this._autointoteamapply = value;
		}
		
	}
}