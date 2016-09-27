package com.game.team.message{
	import com.game.team.bean.SearchPlayerInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 搜索到的玩家列表，返回给前端
	 */
	public class ResGenericSearchToClientMessage extends Message {
	
		//玩家列表
		private var _playerinfolist: Vector.<SearchPlayerInfo> = new Vector.<SearchPlayerInfo>();
		//面板类型(返回给前端)，1组队，2好友，3行会，4聊天
		private var _paneltype: int;
		
		//排序，1按照等级
		private var _sort: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//玩家列表
			writeShort(_playerinfolist.length);
			for (i = 0; i < _playerinfolist.length; i++) {
				writeBean(_playerinfolist[i]);
			}
			//面板类型(返回给前端)，1组队，2好友，3行会，4聊天
			writeByte(_paneltype);
			//排序，1按照等级
			writeByte(_sort);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//玩家列表
			var playerinfolist_length : int = readShort();
			for (i = 0; i < playerinfolist_length; i++) {
				_playerinfolist[i] = readBean(SearchPlayerInfo) as SearchPlayerInfo;
			}
			//面板类型(返回给前端)，1组队，2好友，3行会，4聊天
			_paneltype = readByte();
			//排序，1按照等级
			_sort = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118111;
		}
		
		/**
		 * get 玩家列表
		 * @return 
		 */
		public function get playerinfolist(): Vector.<SearchPlayerInfo>{
			return _playerinfolist;
		}
		
		/**
		 * set 玩家列表
		 */
		public function set playerinfolist(value: Vector.<SearchPlayerInfo>): void{
			this._playerinfolist = value;
		}
		
		/**
		 * get 面板类型(返回给前端)，1组队，2好友，3行会，4聊天
		 * @return 
		 */
		public function get paneltype(): int{
			return _paneltype;
		}
		
		/**
		 * set 面板类型(返回给前端)，1组队，2好友，3行会，4聊天
		 */
		public function set paneltype(value: int): void{
			this._paneltype = value;
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