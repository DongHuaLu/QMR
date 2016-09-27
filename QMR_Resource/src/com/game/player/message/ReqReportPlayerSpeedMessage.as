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
	 * 举报玩家加速
	 */
	public class ReqReportPlayerSpeedMessage extends Message {
	
		//被举报玩家ID
		private var _targetid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//被举报玩家ID
			writeLong(_targetid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//被举报玩家ID
			_targetid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103215;
		}
		
		/**
		 * get 被举报玩家ID
		 * @return 
		 */
		public function get targetid(): long{
			return _targetid;
		}
		
		/**
		 * set 被举报玩家ID
		 */
		public function set targetid(value: long): void{
			this._targetid = value;
		}
		
	}
}