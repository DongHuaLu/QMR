package com.game.epalace.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 完成任务
	 */
	public class ReqEpalaceTaskEndToGameMessage extends Message {
	
		//完成任务，0手动，1用元宝完成
		private var _type: int;
		
		//任务ID
		private var _taskid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//完成任务，0手动，1用元宝完成
			writeByte(_type);
			//任务ID
			writeInt(_taskid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//完成任务，0手动，1用元宝完成
			_type = readByte();
			//任务ID
			_taskid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143203;
		}
		
		/**
		 * get 完成任务，0手动，1用元宝完成
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 完成任务，0手动，1用元宝完成
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 任务ID
		 * @return 
		 */
		public function get taskid(): int{
			return _taskid;
		}
		
		/**
		 * set 任务ID
		 */
		public function set taskid(value: int): void{
			this._taskid = value;
		}
		
	}
}