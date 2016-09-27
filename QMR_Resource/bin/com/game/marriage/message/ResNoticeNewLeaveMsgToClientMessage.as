package com.game.marriage.message{
	import com.game.utils.long;
	import com.game.marriage.bean.LeaveMsgInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知新留言
	 */
	public class ResNoticeNewLeaveMsgToClientMessage extends Message {
	
		//被通知玩家ID
		private var _playerid: long;
		
		//留言列表
		private var _leavemsglist: Vector.<LeaveMsgInfo> = new Vector.<LeaveMsgInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//被通知玩家ID
			writeLong(_playerid);
			//留言列表
			writeShort(_leavemsglist.length);
			for (i = 0; i < _leavemsglist.length; i++) {
				writeBean(_leavemsglist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//被通知玩家ID
			_playerid = readLong();
			//留言列表
			var leavemsglist_length : int = readShort();
			for (i = 0; i < leavemsglist_length; i++) {
				_leavemsglist[i] = readBean(LeaveMsgInfo) as LeaveMsgInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163111;
		}
		
		/**
		 * get 被通知玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 被通知玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 留言列表
		 * @return 
		 */
		public function get leavemsglist(): Vector.<LeaveMsgInfo>{
			return _leavemsglist;
		}
		
		/**
		 * set 留言列表
		 */
		public function set leavemsglist(value: Vector.<LeaveMsgInfo>): void{
			this._leavemsglist = value;
		}
		
	}
}