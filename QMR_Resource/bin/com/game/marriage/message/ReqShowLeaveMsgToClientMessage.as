package com.game.marriage.message{
	import com.game.marriage.bean.LeaveMsgInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 留言展示
	 */
	public class ReqShowLeaveMsgToClientMessage extends Message {
	
		//留言列表
		private var _leavemsglist: Vector.<LeaveMsgInfo> = new Vector.<LeaveMsgInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
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
			return 163104;
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