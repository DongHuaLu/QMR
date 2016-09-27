package com.game.team.bean{
	import com.game.utils.long;
	import com.game.team.bean.TeamMemberInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 组队信息类
	 */
	public class TeamInfo extends Bean {
	
		//队伍Id
		private var _teamid: long;
		
		//自动接受入队申请 1自动
		private var _autoIntoteamapply: int;
		
		//队伍列表
		private var _memberinfo: Vector.<TeamMemberInfo> = new Vector.<TeamMemberInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队伍Id
			writeLong(_teamid);
			//自动接受入队申请 1自动
			writeByte(_autoIntoteamapply);
			//队伍列表
			writeShort(_memberinfo.length);
			for (var i: int = 0; i < _memberinfo.length; i++) {
				writeBean(_memberinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队伍Id
			_teamid = readLong();
			//自动接受入队申请 1自动
			_autoIntoteamapply = readByte();
			//队伍列表
			var memberinfo_length : int = readShort();
			for (var i: int = 0; i < memberinfo_length; i++) {
				_memberinfo[i] = readBean(TeamMemberInfo) as TeamMemberInfo;
			}
			return true;
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
		 * get 自动接受入队申请 1自动
		 * @return 
		 */
		public function get autoIntoteamapply(): int{
			return _autoIntoteamapply;
		}
		
		/**
		 * set 自动接受入队申请 1自动
		 */
		public function set autoIntoteamapply(value: int): void{
			this._autoIntoteamapply = value;
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