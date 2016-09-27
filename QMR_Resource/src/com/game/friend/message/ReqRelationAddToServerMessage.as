package com.game.friend.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 添加关系信息
	 */
	public class ReqRelationAddToServerMessage extends Message {
	
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		private var _btListType: int;
		
		//被操作角色Id
		private var _operateplayerId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			writeByte(_btListType);
			//被操作角色Id
			writeLong(_operateplayerId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			_btListType = readByte();
			//被操作角色Id
			_operateplayerId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 119202;
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
		 * get 被操作角色Id
		 * @return 
		 */
		public function get operateplayerId(): long{
			return _operateplayerId;
		}
		
		/**
		 * set 被操作角色Id
		 */
		public function set operateplayerId(value: long): void{
			this._operateplayerId = value;
		}
		
	}
}