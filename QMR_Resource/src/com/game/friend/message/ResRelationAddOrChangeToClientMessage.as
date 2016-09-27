package com.game.friend.message{
	import com.game.friend.bean.RelationInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 添加或者更新一个新关系
	 */
	public class ResRelationAddOrChangeToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//添加或更新的关系信息
		private var _relationAddInfo: RelationInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//添加或更新的关系信息
			writeBean(_relationAddInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_btErrorCode = readByte();
			//添加或更新的关系信息
			_relationAddInfo = readBean(RelationInfo) as RelationInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 119102;
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
		 * get 添加或更新的关系信息
		 * @return 
		 */
		public function get relationAddInfo(): RelationInfo{
			return _relationAddInfo;
		}
		
		/**
		 * set 添加或更新的关系信息
		 */
		public function set relationAddInfo(value: RelationInfo): void{
			this._relationAddInfo = value;
		}
		
	}
}