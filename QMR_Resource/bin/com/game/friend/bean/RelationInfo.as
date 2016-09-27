package com.game.friend.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 关系信息
	 */
	public class RelationInfo extends Bean {
	
		//角色Id
		private var _lgUserId: long;
		
		//平台VIP
		private var _webvip: int;
		
		//角色名
		private var _szName: String;
		
		//人气
		private var _nPopularity: int;
		
		//等级
		private var _nLevel: int;
		
		//人物头像
		private var _nIcon: int;
		
		//性别
		private var _btSex: int;
		
		//职业
		private var _btJob: int;
		
		//地图Id
		private var _nMapId: int;
		
		//心情
		private var _szMood: String;
		
		//历史战绩
		private var _nRelationDegree: int;
		
		//添加时间
		private var _nAddTime: int;
		
		//状态 2 摆摊 1 在线 0 离线
		private var _btState: int;
		
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		private var _btListType: int;
		
		//关系排序位置
		private var _btSortIdx: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_lgUserId);
			//平台VIP
			writeInt(_webvip);
			//角色名
			writeString(_szName);
			//人气
			writeInt(_nPopularity);
			//等级
			writeInt(_nLevel);
			//人物头像
			writeInt(_nIcon);
			//性别
			writeByte(_btSex);
			//职业
			writeByte(_btJob);
			//地图Id
			writeInt(_nMapId);
			//心情
			writeString(_szMood);
			//历史战绩
			writeInt(_nRelationDegree);
			//添加时间
			writeInt(_nAddTime);
			//状态 2 摆摊 1 在线 0 离线
			writeByte(_btState);
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			writeByte(_btListType);
			//关系排序位置
			writeByte(_btSortIdx);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_lgUserId = readLong();
			//平台VIP
			_webvip = readInt();
			//角色名
			_szName = readString();
			//人气
			_nPopularity = readInt();
			//等级
			_nLevel = readInt();
			//人物头像
			_nIcon = readInt();
			//性别
			_btSex = readByte();
			//职业
			_btJob = readByte();
			//地图Id
			_nMapId = readInt();
			//心情
			_szMood = readString();
			//历史战绩
			_nRelationDegree = readInt();
			//添加时间
			_nAddTime = readInt();
			//状态 2 摆摊 1 在线 0 离线
			_btState = readByte();
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			_btListType = readByte();
			//关系排序位置
			_btSortIdx = readByte();
			return true;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get lgUserId(): long{
			return _lgUserId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set lgUserId(value: long): void{
			this._lgUserId = value;
		}
		
		/**
		 * get 平台VIP
		 * @return 
		 */
		public function get webvip(): int{
			return _webvip;
		}
		
		/**
		 * set 平台VIP
		 */
		public function set webvip(value: int): void{
			this._webvip = value;
		}
		
		/**
		 * get 角色名
		 * @return 
		 */
		public function get szName(): String{
			return _szName;
		}
		
		/**
		 * set 角色名
		 */
		public function set szName(value: String): void{
			this._szName = value;
		}
		
		/**
		 * get 人气
		 * @return 
		 */
		public function get nPopularity(): int{
			return _nPopularity;
		}
		
		/**
		 * set 人气
		 */
		public function set nPopularity(value: int): void{
			this._nPopularity = value;
		}
		
		/**
		 * get 等级
		 * @return 
		 */
		public function get nLevel(): int{
			return _nLevel;
		}
		
		/**
		 * set 等级
		 */
		public function set nLevel(value: int): void{
			this._nLevel = value;
		}
		
		/**
		 * get 人物头像
		 * @return 
		 */
		public function get nIcon(): int{
			return _nIcon;
		}
		
		/**
		 * set 人物头像
		 */
		public function set nIcon(value: int): void{
			this._nIcon = value;
		}
		
		/**
		 * get 性别
		 * @return 
		 */
		public function get btSex(): int{
			return _btSex;
		}
		
		/**
		 * set 性别
		 */
		public function set btSex(value: int): void{
			this._btSex = value;
		}
		
		/**
		 * get 职业
		 * @return 
		 */
		public function get btJob(): int{
			return _btJob;
		}
		
		/**
		 * set 职业
		 */
		public function set btJob(value: int): void{
			this._btJob = value;
		}
		
		/**
		 * get 地图Id
		 * @return 
		 */
		public function get nMapId(): int{
			return _nMapId;
		}
		
		/**
		 * set 地图Id
		 */
		public function set nMapId(value: int): void{
			this._nMapId = value;
		}
		
		/**
		 * get 心情
		 * @return 
		 */
		public function get szMood(): String{
			return _szMood;
		}
		
		/**
		 * set 心情
		 */
		public function set szMood(value: String): void{
			this._szMood = value;
		}
		
		/**
		 * get 历史战绩
		 * @return 
		 */
		public function get nRelationDegree(): int{
			return _nRelationDegree;
		}
		
		/**
		 * set 历史战绩
		 */
		public function set nRelationDegree(value: int): void{
			this._nRelationDegree = value;
		}
		
		/**
		 * get 添加时间
		 * @return 
		 */
		public function get nAddTime(): int{
			return _nAddTime;
		}
		
		/**
		 * set 添加时间
		 */
		public function set nAddTime(value: int): void{
			this._nAddTime = value;
		}
		
		/**
		 * get 状态 2 摆摊 1 在线 0 离线
		 * @return 
		 */
		public function get btState(): int{
			return _btState;
		}
		
		/**
		 * set 状态 2 摆摊 1 在线 0 离线
		 */
		public function set btState(value: int): void{
			this._btState = value;
		}
		
		/**
		 * get 关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		 * @return 
		 */
		public function get btListType(): int{
			return _btListType;
		}
		
		/**
		 * set 关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		 */
		public function set btListType(value: int): void{
			this._btListType = value;
		}
		
		/**
		 * get 关系排序位置
		 * @return 
		 */
		public function get btSortIdx(): int{
			return _btSortIdx;
		}
		
		/**
		 * set 关系排序位置
		 */
		public function set btSortIdx(value: int): void{
			this._btSortIdx = value;
		}
		
	}
}