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
	 * 组队回应
	 */
	public class ResTeamRespondToClientMessage extends Message {
	
		//玩家Id
		private var _playerid: long;
		
		//1邀请组队，2申请加入
		private var _type: int;
		
		//1同意，2拒绝
		private var _respond: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家Id
			writeLong(_playerid);
			//1邀请组队，2申请加入
			writeInt(_type);
			//1同意，2拒绝
			writeByte(_respond);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家Id
			_playerid = readLong();
			//1邀请组队，2申请加入
			_type = readInt();
			//1同意，2拒绝
			_respond = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118114;
		}
		
		/**
		 * get 玩家Id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家Id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 1邀请组队，2申请加入
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 1邀请组队，2申请加入
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 1同意，2拒绝
		 * @return 
		 */
		public function get respond(): int{
			return _respond;
		}
		
		/**
		 * set 1同意，2拒绝
		 */
		public function set respond(value: int): void{
			this._respond = value;
		}
		
	}
}