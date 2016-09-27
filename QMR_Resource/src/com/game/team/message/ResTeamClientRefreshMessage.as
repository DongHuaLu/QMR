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
	 * 更新队伍信息（通用）
	 */
	public class ResTeamClientRefreshMessage extends Message {
	
		//队伍Id
		private var _teamid: long;
		
		//队伍列表
		private var _memberinfo: Vector.<TeamMemberInfo> = new Vector.<TeamMemberInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//队伍Id
			writeLong(_teamid);
			//队伍列表
			writeShort(_memberinfo.length);
			for (i = 0; i < _memberinfo.length; i++) {
				writeBean(_memberinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//队伍Id
			_teamid = readLong();
			//队伍列表
			var memberinfo_length : int = readShort();
			for (i = 0; i < memberinfo_length; i++) {
				_memberinfo[i] = readBean(TeamMemberInfo) as TeamMemberInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118101;
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
		 * get 队伍列表
		 * @return 
		 */
		public function get memberinfo(): Vector.<TeamMemberInfo>{
			return _memberinfo;
		}
		
		/**
		 * set 队伍列表
		 */
		public function set memberinfo(value: Vector.<TeamMemberInfo>): void{
			this._memberinfo = value;
		}
		
	}
}