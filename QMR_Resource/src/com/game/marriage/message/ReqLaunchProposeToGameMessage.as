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
	 * 玩家发起求婚（准备）
	 */
	public class ReqLaunchProposeToGameMessage extends Message {
	
		//求婚对象ID
		private var _suitorobjid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//求婚对象ID
			writeLong(_suitorobjid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//求婚对象ID
			_suitorobjid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163201;
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
		
	}
}