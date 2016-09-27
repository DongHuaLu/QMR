package com.game.guild.message{
	import com.game.guild.bean.MemberInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取成员列表返回
	 */
	public class ResGuildGetMemberListToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//所有成员列表
		private var _memberList: Vector.<MemberInfo> = new Vector.<MemberInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_btErrorCode);
			//所有成员列表
			writeShort(_memberList.length);
			for (i = 0; i < _memberList.length; i++) {
				writeBean(_memberList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//错误代码
			_btErrorCode = readByte();
			//所有成员列表
			var memberList_length : int = readShort();
			for (i = 0; i < memberList_length; i++) {
				_memberList[i] = readBean(MemberInfo) as MemberInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121106;
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
		 * get 所有成员列表
		 * @return 
		 */
		public function get memberList(): Vector.<MemberInfo>{
			return _memberList;
		}
		
		/**
		 * set 所有成员列表
		 */
		public function set memberList(value: Vector.<MemberInfo>): void{
			this._memberList = value;
		}
		
	}
}