package com.game.zones.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 副本报名名单
	 */
	public class ZoneApplyDataInfo extends Bean {
	
		//副本id
		private var _zoneid: int;
		
		//玩家名字列表
		private var _playernamelist: Vector.<String> = new Vector.<String>();
		//玩家等级列表
		private var _playerlvlist: Vector.<int> = new Vector.<int>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//副本id
			writeInt(_zoneid);
			//玩家名字列表
			writeShort(_playernamelist.length);
			for (var i: int = 0; i < _playernamelist.length; i++) {
				writeString(_playernamelist[i]);
			}
			//玩家等级列表
			writeShort(_playerlvlist.length);
			for (var i: int = 0; i < _playerlvlist.length; i++) {
				writeInt(_playerlvlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本id
			_zoneid = readInt();
			//玩家名字列表
			var playernamelist_length : int = readShort();
			for (var i: int = 0; i < playernamelist_length; i++) {
				_playernamelist[i] = readString();
			}
			//玩家等级列表
			var playerlvlist_length : int = readShort();
			for (var i: int = 0; i < playerlvlist_length; i++) {
				_playerlvlist[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get 副本id
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本id
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 玩家名字列表
		 * @return 
		 */
		public function get playernamelist(): Vector.<String>{
			return _playernamelist;
		}
		
		/**
		 * set 玩家名字列表
		 */
		public function set playernamelist(value: Vector.<String>): void{
			this._playernamelist = value;
		}
		
		/**
		 * get 玩家等级列表
		 * @return 
		 */
		public function get playerlvlist(): Vector.<int>{
			return _playerlvlist;
		}
		
		/**
		 * set 玩家等级列表
		 */
		public function set playerlvlist(value: Vector.<int>): void{
			this._playerlvlist = value;
		}
		
	}
}