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
	 * 远程入队，在不知道队伍ID的情况下，服务器做判断是邀请还是加入
	 */
	public class ReqIntoTeamToGameMessage extends Message {
	
		//他人ID
		private var _othersid: long;
		
		//0普通邀请，1跨服战队邀请
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//他人ID
			writeLong(_othersid);
			//0普通邀请，1跨服战队邀请
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//他人ID
			_othersid = readLong();
			//0普通邀请，1跨服战队邀请
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118216;
		}
		
		/**
		 * get 他人ID
		 * @return 
		 */
		public function get othersid(): long{
			return _othersid;
		}
		
		/**
		 * set 他人ID
		 */
		public function set othersid(value: long): void{
			this._othersid = value;
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