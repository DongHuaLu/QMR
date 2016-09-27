package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通关选择奖励-翻牌
	 */
	public class ReqSelectAwardMessage extends Message {
	
		//翻牌编号（0-11）
		private var _idx: int;
		
		//类型:0手动，1自动扫荡
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//翻牌编号（0-11）
			writeInt(_idx);
			//类型:0手动，1自动扫荡
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//翻牌编号（0-11）
			_idx = readInt();
			//类型:0手动，1自动扫荡
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128203;
		}
		
		/**
		 * get 翻牌编号（0-11）
		 * @return 
		 */
		public function get idx(): int{
			return _idx;
		}
		
		/**
		 * set 翻牌编号（0-11）
		 */
		public function set idx(value: int): void{
			this._idx = value;
		}
		
		/**
		 * get 类型:0手动，1自动扫荡
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型:0手动，1自动扫荡
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}