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
	 * 入队申请-通知队长做选择
	 */
	public class ResApplyClientMessage extends Message {
	
		//队伍Id
		private var _teamid: long;
		
		//新队员信息
		private var _newmemberinfo: TeamMemberInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队伍Id
			writeLong(_teamid);
			//新队员信息
			writeBean(_newmemberinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队伍Id
			_teamid = readLong();
			//新队员信息
			_newmemberinfo = readBean(TeamMemberInfo) as TeamMemberInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118102;
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
		 * get 新队员信息
		 * @return 
		 */
		public function get newmemberinfo(): TeamMemberInfo{
			return _newmemberinfo;
		}
		
		/**
		 * set 新队员信息
		 */
		public function set newmemberinfo(value: TeamMemberInfo): void{
			this._newmemberinfo = value;
		}
		
	}
}