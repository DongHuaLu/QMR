package com.game.task.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 任务完成
	 */
	public class ResTaskFinshMessage extends Message {
	
		//任务类型 主线 日常 讨伐
		private var _type: int;
		
		//任务模型
		private var _modelId: int;
		
		//吞噬任务ID
		private var _conquerTadkId: long;
		
		//日常任务完成类型   0普通完成 1一键完成 
		private var _finshType: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务类型 主线 日常 讨伐
			writeByte(_type);
			//任务模型
			writeInt(_modelId);
			//吞噬任务ID
			writeLong(_conquerTadkId);
			//日常任务完成类型   0普通完成 1一键完成 
			writeInt(_finshType);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务类型 主线 日常 讨伐
			_type = readByte();
			//任务模型
			_modelId = readInt();
			//吞噬任务ID
			_conquerTadkId = readLong();
			//日常任务完成类型   0普通完成 1一键完成 
			_finshType = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120102;
		}
		
		/**
		 * get 任务类型 主线 日常 讨伐
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 任务类型 主线 日常 讨伐
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 任务模型
		 * @return 
		 */
		public function get modelId(): int{
			return _modelId;
		}
		
		/**
		 * set 任务模型
		 */
		public function set modelId(value: int): void{
			this._modelId = value;
		}
		
		/**
		 * get 吞噬任务ID
		 * @return 
		 */
		public function get conquerTadkId(): long{
			return _conquerTadkId;
		}
		
		/**
		 * set 吞噬任务ID
		 */
		public function set conquerTadkId(value: long): void{
			this._conquerTadkId = value;
		}
		
		/**
		 * get 日常任务完成类型   0普通完成 1一键完成 
		 * @return 
		 */
		public function get finshType(): int{
			return _finshType;
		}
		
		/**
		 * set 日常任务完成类型   0普通完成 1一键完成 
		 */
		public function set finshType(value: int): void{
			this._finshType = value;
		}
		
	}
}