package com.game.team.bean{
	import com.game.utils.long;
	import com.game.player.bean.PlayerAppearanceInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 队员信息
	 */
	public class TeamMemberInfo extends Bean {
	
		//队员ID
		private var _memberid: long;
		
		//队员名字
		private var _membername: String;
		
		//队员等级
		private var _memberlevel: int;
		
		//队员所在地图
		private var _membermapid: int;
		
		//队员所在线路
		private var _memberline: int;
		
		//队员所在坐标X
		private var _mx: int;
		
		//队员所在坐标Y
		private var _my: int;
		
		//地图唯一ID
		private var _membermaponlyid: long;
		
		//造型信息
		private var _appearanceInfo: com.game.player.bean.PlayerAppearanceInfo;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队员ID
			writeLong(_memberid);
			//队员名字
			writeString(_membername);
			//队员等级
			writeInt(_memberlevel);
			//队员所在地图
			writeInt(_membermapid);
			//队员所在线路
			writeInt(_memberline);
			//队员所在坐标X
			writeShort(_mx);
			//队员所在坐标Y
			writeShort(_my);
			//地图唯一ID
			writeLong(_membermaponlyid);
			//造型信息
			writeBean(_appearanceInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队员ID
			_memberid = readLong();
			//队员名字
			_membername = readString();
			//队员等级
			_memberlevel = readInt();
			//队员所在地图
			_membermapid = readInt();
			//队员所在线路
			_memberline = readInt();
			//队员所在坐标X
			_mx = readShort();
			//队员所在坐标Y
			_my = readShort();
			//地图唯一ID
			_membermaponlyid = readLong();
			//造型信息
			_appearanceInfo = readBean(com.game.player.bean.PlayerAppearanceInfo) as com.game.player.bean.PlayerAppearanceInfo;
			return true;
		}
		
		/**
		 * get 队员ID
		 * @return 
		 */
		public function get memberid(): long{
			return _memberid;
		}
		
		/**
		 * set 队员ID
		 */
		public function set memberid(value: long): void{
			this._memberid = value;
		}
		
		/**
		 * get 队员名字
		 * @return 
		 */
		public function get membername(): String{
			return _membername;
		}
		
		/**
		 * set 队员名字
		 */
		public function set membername(value: String): void{
			this._membername = value;
		}
		
		/**
		 * get 队员等级
		 * @return 
		 */
		public function get memberlevel(): int{
			return _memberlevel;
		}
		
		/**
		 * set 队员等级
		 */
		public function set memberlevel(value: int): void{
			this._memberlevel = value;
		}
		
		/**
		 * get 队员所在地图
		 * @return 
		 */
		public function get membermapid(): int{
			return _membermapid;
		}
		
		/**
		 * set 队员所在地图
		 */
		public function set membermapid(value: int): void{
			this._membermapid = value;
		}
		
		/**
		 * get 队员所在线路
		 * @return 
		 */
		public function get memberline(): int{
			return _memberline;
		}
		
		/**
		 * set 队员所在线路
		 */
		public function set memberline(value: int): void{
			this._memberline = value;
		}
		
		/**
		 * get 队员所在坐标X
		 * @return 
		 */
		public function get mx(): int{
			return _mx;
		}
		
		/**
		 * set 队员所在坐标X
		 */
		public function set mx(value: int): void{
			this._mx = value;
		}
		
		/**
		 * get 队员所在坐标Y
		 * @return 
		 */
		public function get my(): int{
			return _my;
		}
		
		/**
		 * set 队员所在坐标Y
		 */
		public function set my(value: int): void{
			this._my = value;
		}
		
		/**
		 * get 地图唯一ID
		 * @return 
		 */
		public function get membermaponlyid(): long{
			return _membermaponlyid;
		}
		
		/**
		 * set 地图唯一ID
		 */
		public function set membermaponlyid(value: long): void{
			this._membermaponlyid = value;
		}
		
		/**
		 * get 造型信息
		 * @return 
		 */
		public function get appearanceInfo(): com.game.player.bean.PlayerAppearanceInfo{
			return _appearanceInfo;
		}
		
		/**
		 * set 造型信息
		 */
		public function set appearanceInfo(value: com.game.player.bean.PlayerAppearanceInfo): void{
			this._appearanceInfo = value;
		}
		
	}
}