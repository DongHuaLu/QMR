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
	 * 邀请入队
	 */
	public class ReqInviteGameMessage extends Message {
	
		//队伍Id
		private var _teamid: long;
		
		//玩家ID
		private var _playerid: long;
		
		//0普通邀请，1跨服战队邀请
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队伍Id
			writeLong(_teamid);
			//玩家ID
			writeLong(_playerid);
			//0普通邀请，1跨服战队邀请
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队伍Id
			_teamid = readLong();
			//玩家ID
			_playerid = readLong();
			//0普通邀请，1跨服战队邀请
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118204;
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
		 * get 玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 0普通邀请，1跨服战队邀请
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0普通邀请，1跨服战队邀请
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}