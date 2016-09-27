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
	 * 发送单独成员信息给客户端
	 */
	public class ResGuildAloneMemberInfoToClientMessage extends Message {
	
		//通知类型 0 创建 1 添加或更新 2 删除 等
		private var _notifyType: int;
		
		//单独成员信息
		private var _memberInfo: MemberInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//通知类型 0 创建 1 添加或更新 2 删除 等
			writeByte(_notifyType);
			//单独成员信息
			writeBean(_memberInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//通知类型 0 创建 1 添加或更新 2 删除 等
			_notifyType = readByte();
			//单独成员信息
			_memberInfo = readBean(MemberInfo) as MemberInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121391;
		}
		
		/**
		 * get 通知类型 0 创建 1 添加或更新 2 删除 等
		 * @return 
		 */
		public function get notifyType(): int{
			return _notifyType;
		}
		
		/**
		 * set 通知类型 0 创建 1 添加或更新 2 删除 等
		 */
		public function set notifyType(value: int): void{
			this._notifyType = value;
		}
		
		/**
		 * get 单独成员信息
		 * @return 
		 */
		public function get memberInfo(): MemberInfo{
			return _memberInfo;
		}
		
		/**
		 * set 单独成员信息
		 */
		public function set memberInfo(value: MemberInfo): void{
			this._memberInfo = value;
		}
		
	}
}