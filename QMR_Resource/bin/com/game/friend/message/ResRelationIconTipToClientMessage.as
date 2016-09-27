package com.game.friend.message{
	import com.game.friend.bean.FriendModeInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 对被操作人的Icon提示返回
	 */
	public class ResRelationIconTipToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		private var _btListType: int;
		
		//操作者信息
		private var _friendModeInfo: FriendModeInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			writeByte(_btListType);
			//操作者信息
			writeBean(_friendModeInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_btErrorCode = readByte();
			//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
			_btListType = readByte();
			//操作者信息
			_friendModeInfo = readBean(FriendModeInfo) as FriendModeInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 119104;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get btErrorCode(): int{
			return _btErrorCode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set btErrorCode(value: int): void{
			this._btErrorCode = value;
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
		 * get 操作者信息
		 * @return 
		 */
		public function get friendModeInfo(): FriendModeInfo{
			return _friendModeInfo;
		}
		
		/**
		 * set 操作者信息
		 */
		public function set friendModeInfo(value: FriendModeInfo): void{
			this._friendModeInfo = value;
		}
		
	}
}