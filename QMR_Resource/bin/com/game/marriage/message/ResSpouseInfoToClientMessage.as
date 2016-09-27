package com.game.marriage.message{
	import com.game.player.bean.OtherPlayerInfo;
	import com.game.marriage.bean.LeaveMsgInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取配偶面板信息
	 */
	public class ResSpouseInfoToClientMessage extends Message {
	
		//配偶信息
		private var _otherPlayerInfo: com.game.player.bean.OtherPlayerInfo;
		
		//留言列表
		private var _leavemsglist: Vector.<LeaveMsgInfo> = new Vector.<LeaveMsgInfo>();
		//戒指模版ID
		private var _ringmodelid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//配偶信息
			writeBean(_otherPlayerInfo);
			//留言列表
			writeShort(_leavemsglist.length);
			for (i = 0; i < _leavemsglist.length; i++) {
				writeBean(_leavemsglist[i]);
			}
			//戒指模版ID
			writeInt(_ringmodelid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//配偶信息
			_otherPlayerInfo = readBean(com.game.player.bean.OtherPlayerInfo) as com.game.player.bean.OtherPlayerInfo;
			//留言列表
			var leavemsglist_length : int = readShort();
			for (i = 0; i < leavemsglist_length; i++) {
				_leavemsglist[i] = readBean(LeaveMsgInfo) as LeaveMsgInfo;
			}
			//戒指模版ID
			_ringmodelid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163110;
		}
		
		/**
		 * get 配偶信息
		 * @return 
		 */
		public function get otherPlayerInfo(): com.game.player.bean.OtherPlayerInfo{
			return _otherPlayerInfo;
		}
		
		/**
		 * set 配偶信息
		 */
		public function set otherPlayerInfo(value: com.game.player.bean.OtherPlayerInfo): void{
			this._otherPlayerInfo = value;
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
		
		/**
		 * get 戒指模版ID
		 * @return 
		 */
		public function get ringmodelid(): int{
			return _ringmodelid;
		}
		
		/**
		 * set 戒指模版ID
		 */
		public function set ringmodelid(value: int): void{
			this._ringmodelid = value;
		}
		
	}
}