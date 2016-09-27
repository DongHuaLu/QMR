package com.game.dataserver.message{
	import com.game.dataserver.bean.InvitePlayerInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送邀请列表到前端
	 */
	public class ResGetinvitelistToClientMessage extends Message {
	
		//邀请列表
		private var _InvitePlayerInfolist: Vector.<InvitePlayerInfo> = new Vector.<InvitePlayerInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//邀请列表
			writeShort(_InvitePlayerInfolist.length);
			for (i = 0; i < _InvitePlayerInfolist.length; i++) {
				writeBean(_InvitePlayerInfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//邀请列表
			var InvitePlayerInfolist_length : int = readShort();
			for (i = 0; i < InvitePlayerInfolist_length; i++) {
				_InvitePlayerInfolist[i] = readBean(InvitePlayerInfo) as InvitePlayerInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203113;
		}
		
		/**
		 * get 邀请列表
		 * @return 
		 */
		public function get InvitePlayerInfolist(): Vector.<InvitePlayerInfo>{
			return _InvitePlayerInfolist;
		}
		
		/**
		 * set 邀请列表
		 */
		public function set InvitePlayerInfolist(value: Vector.<InvitePlayerInfo>): void{
			this._InvitePlayerInfolist = value;
		}
		
	}
}