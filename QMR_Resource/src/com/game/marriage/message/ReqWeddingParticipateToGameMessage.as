package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 送上红包，参加婚宴
	 */
	public class ReqWeddingParticipateToGameMessage extends Message {
	
		//红包数量
		private var _num: int;
		
		//结婚id
		private var _marriageid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//红包数量
			writeInt(_num);
			//结婚id
			writeLong(_marriageid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//红包数量
			_num = readInt();
			//结婚id
			_marriageid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163212;
		}
		
		/**
		 * get 红包数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 红包数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 结婚id
		 * @return 
		 */
		public function get marriageid(): long{
			return _marriageid;
		}
		
		/**
		 * set 结婚id
		 */
		public function set marriageid(value: long): void{
			this._marriageid = value;
		}
		
	}
}