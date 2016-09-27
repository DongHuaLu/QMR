package com.game.guild.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 添加外交关系返回
	 */
	public class ResGuildAddDiplomaticToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//外交关系类型
		private var _diplomaticType: int;
		
		//申请操作帮会Id
		private var _applyGuildId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//外交关系类型
			writeByte(_diplomaticType);
			//申请操作帮会Id
			writeLong(_applyGuildId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_btErrorCode = readByte();
			//外交关系类型
			_diplomaticType = readByte();
			//申请操作帮会Id
			_applyGuildId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121118;
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
		 * get 外交关系类型
		 * @return 
		 */
		public function get diplomaticType(): int{
			return _diplomaticType;
		}
		
		/**
		 * set 外交关系类型
		 */
		public function set diplomaticType(value: int): void{
			this._diplomaticType = value;
		}
		
		/**
		 * get 申请操作帮会Id
		 * @return 
		 */
		public function get applyGuildId(): long{
			return _applyGuildId;
		}
		
		/**
		 * set 申请操作帮会Id
		 */
		public function set applyGuildId(value: long): void{
			this._applyGuildId = value;
		}
		
	}
}