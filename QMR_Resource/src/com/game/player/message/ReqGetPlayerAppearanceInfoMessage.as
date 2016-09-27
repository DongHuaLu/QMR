package com.game.player.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 得到玩家造型信息
	 */
	public class ReqGetPlayerAppearanceInfoMessage extends Message {
	
		//他人ID
		private var _othersid: long;
		
		//面板类型
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//他人ID
			writeLong(_othersid);
			//面板类型
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//他人ID
			_othersid = readLong();
			//面板类型
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103211;
		}
		
		/**
		 * get 他人ID
		 * @return 
		 */
		public function get othersid(): long{
			return _othersid;
		}
		
		/**
		 * set 他人ID
		 */
		public function set othersid(value: long): void{
			this._othersid = value;
		}
		
		/**
		 * get 面板类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 面板类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}