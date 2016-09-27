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
	 * 进行更换婚戒
	 */
	public class ReqReplaceRingToGameMessage extends Message {
	
		//求婚对象ID
		private var _suitorobjid: long;
		
		//戒指模版ID
		private var _ringmodelid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//求婚对象ID
			writeLong(_suitorobjid);
			//戒指模版ID
			writeInt(_ringmodelid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//求婚对象ID
			_suitorobjid = readLong();
			//戒指模版ID
			_ringmodelid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163204;
		}
		
		/**
		 * get 求婚对象ID
		 * @return 
		 */
		public function get suitorobjid(): long{
			return _suitorobjid;
		}
		
		/**
		 * set 求婚对象ID
		 */
		public function set suitorobjid(value: long): void{
			this._suitorobjid = value;
		}
		
		/**
		 * get 戒指模版ID
		 * @return 
		 */
		public function get ringmodelid(): int{
			return _ringmodelid;
		}
		
		/**
		 * set 戒指模版ID
		 */
		public function set ringmodelid(value: int): void{
			this._ringmodelid = value;
		}
		
	}
}