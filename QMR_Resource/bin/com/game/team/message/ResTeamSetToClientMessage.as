package com.game.team.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 把组队设置发送给前端
	 */
	public class ResTeamSetToClientMessage extends Message {
	
		//自动接受入队申请 ，0手动 1自动申请
		private var _autoIntoteamapply: int;
		
		//自动接受组队邀请，0手动 1自动邀请
		private var _autoTeaminvited: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//自动接受入队申请 ，0手动 1自动申请
			writeByte(_autoIntoteamapply);
			//自动接受组队邀请，0手动 1自动邀请
			writeByte(_autoTeaminvited);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//自动接受入队申请 ，0手动 1自动申请
			_autoIntoteamapply = readByte();
			//自动接受组队邀请，0手动 1自动邀请
			_autoTeaminvited = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118113;
		}
		
		/**
		 * get 自动接受入队申请 ，0手动 1自动申请
		 * @return 
		 */
		public function get autoIntoteamapply(): int{
			return _autoIntoteamapply;
		}
		
		/**
		 * set 自动接受入队申请 ，0手动 1自动申请
		 */
		public function set autoIntoteamapply(value: int): void{
			this._autoIntoteamapply = value;
		}
		
		/**
		 * get 自动接受组队邀请，0手动 1自动邀请
		 * @return 
		 */
		public function get autoTeaminvited(): int{
			return _autoTeaminvited;
		}
		
		/**
		 * set 自动接受组队邀请，0手动 1自动邀请
		 */
		public function set autoTeaminvited(value: int): void{
			this._autoTeaminvited = value;
		}
		
	}
}