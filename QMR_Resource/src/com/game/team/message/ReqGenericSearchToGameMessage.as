package com.game.team.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通用玩家搜索，转发到世界服务器
	 */
	public class ReqGenericSearchToGameMessage extends Message {
	
		//搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
		private var _type: int;
		
		//面板类型，1组队，2好友，3行会，4聊天
		private var _paneltype: int;
		
		//搜索内容
		private var _searchcontent: String;
		
		//索引开始
		private var _indexstart: int;
		
		//索引结束
		private var _indexend: int;
		
		//排序，1按照等级
		private var _sort: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
			writeByte(_type);
			//面板类型，1组队，2好友，3行会，4聊天
			writeByte(_paneltype);
			//搜索内容
			writeString(_searchcontent);
			//索引开始
			writeShort(_indexstart);
			//索引结束
			writeShort(_indexend);
			//排序，1按照等级
			writeByte(_sort);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
			_type = readByte();
			//面板类型，1组队，2好友，3行会，4聊天
			_paneltype = readByte();
			//搜索内容
			_searchcontent = readString();
			//索引开始
			_indexstart = readShort();
			//索引结束
			_indexend = readShort();
			//排序，1按照等级
			_sort = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118215;
		}
		
		/**
		 * get 搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 面板类型，1组队，2好友，3行会，4聊天
		 * @return 
		 */
		public function get paneltype(): int{
			return _paneltype;
		}
		
		/**
		 * set 面板类型，1组队，2好友，3行会，4聊天
		 */
		public function set paneltype(value: int): void{
			this._paneltype = value;
		}
		
		/**
		 * get 搜索内容
		 * @return 
		 */
		public function get searchcontent(): String{
			return _searchcontent;
		}
		
		/**
		 * set 搜索内容
		 */
		public function set searchcontent(value: String): void{
			this._searchcontent = value;
		}
		
		/**
		 * get 索引开始
		 * @return 
		 */
		public function get indexstart(): int{
			return _indexstart;
		}
		
		/**
		 * set 索引开始
		 */
		public function set indexstart(value: int): void{
			this._indexstart = value;
		}
		
		/**
		 * get 索引结束
		 * @return 
		 */
		public function get indexend(): int{
			return _indexend;
		}
		
		/**
		 * set 索引结束
		 */
		public function set indexend(value: int): void{
			this._indexend = value;
		}
		
		/**
		 * get 排序，1按照等级
		 * @return 
		 */
		public function get sort(): int{
			return _sort;
		}
		
		/**
		 * set 排序，1按照等级
		 */
		public function set sort(value: int): void{
			this._sort = value;
		}
		
	}
}