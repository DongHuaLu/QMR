package com.game.team.message{
	import com.game.utils.long;
	import com.game.team.bean.TeamMemberInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 任命新队长-通知玩家做选择
	 */
	public class ResAppointClientMessage extends Message {
	
		//队伍Id
		private var _teamid: long;
		
		//队长信息
		private var _captaininfo: TeamMemberInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队伍Id
			writeLong(_teamid);
			//队长信息
			writeBean(_captaininfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队伍Id
			_teamid = readLong();
			//队长信息
			_captaininfo = readBean(TeamMemberInfo) as TeamMemberInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118104;
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
		 * get 队长信息
		 * @return 
		 */
		public function get captaininfo(): TeamMemberInfo{
			return _captaininfo;
		}
		
		/**
		 * set 队长信息
		 */
		public function set captaininfo(value: TeamMemberInfo): void{
			this._captaininfo = value;
		}
		
	}
}