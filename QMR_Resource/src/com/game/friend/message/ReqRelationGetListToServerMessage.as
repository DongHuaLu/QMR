package com.game.friend.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求关系列表
	 */
	public class ReqRelationGetListToServerMessage extends Message {
	
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		private var _btListType: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			writeByte(_btListType);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			_btListType = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 119201;
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
		
	}
}