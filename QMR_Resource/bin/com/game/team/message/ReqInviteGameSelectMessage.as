package com.game.team.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 邀请入队-玩家选择
	 */
	public class ReqInviteGameSelectMessage extends Message {
	
		//队伍Id
		private var _teamid: long;
		
		//邀请人ID
		private var _captainid: long;
		
		//0同意，1拒绝
		private var _select: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队伍Id
			writeLong(_teamid);
			//邀请人ID
			writeLong(_captainid);
			//0同意，1拒绝
			writeByte(_select);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队伍Id
			_teamid = readLong();
			//邀请人ID
			_captainid = readLong();
			//0同意，1拒绝
			_select = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118205;
		}
		
		/**
		 * get 队伍Id
		 * @return 
		 */
		public function get teamid(): long{
			return _teamid;
		}
		
		/**
		 * set 队伍Id
		 */
		public function set teamid(value: long): void{
			this._teamid = value;
		}
		
		/**
		 * get 邀请人ID
		 * @return 
		 */
		public function get captainid(): long{
			return _captainid;
		}
		
		/**
		 * set 邀请人ID
		 */
		public function set captainid(value: long): void{
			this._captainid = value;
		}
		
		/**
		 * get 0同意，1拒绝
		 * @return 
		 */
		public function get select(): int{
			return _select;
		}
		
		/**
		 * set 0同意，1拒绝
		 */
		public function set select(value: int): void{
			this._select = value;
		}
		
	}
}