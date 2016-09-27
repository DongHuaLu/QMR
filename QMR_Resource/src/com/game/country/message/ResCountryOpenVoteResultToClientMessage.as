package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知玩家投票结果
	 */
	public class ResCountryOpenVoteResultToClientMessage extends Message {
	
		//BUFFID
		private var _buffmodelid: int;
		
		//票数
		private var _votenum: int;
		
		//倒计时（秒）
		private var _ms: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//BUFFID
			writeInt(_buffmodelid);
			//票数
			writeInt(_votenum);
			//倒计时（秒）
			writeInt(_ms);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//BUFFID
			_buffmodelid = readInt();
			//票数
			_votenum = readInt();
			//倒计时（秒）
			_ms = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146116;
		}
		
		/**
		 * get BUFFID
		 * @return 
		 */
		public function get buffmodelid(): int{
			return _buffmodelid;
		}
		
		/**
		 * set BUFFID
		 */
		public function set buffmodelid(value: int): void{
			this._buffmodelid = value;
		}
		
		/**
		 * get 票数
		 * @return 
		 */
		public function get votenum(): int{
			return _votenum;
		}
		
		/**
		 * set 票数
		 */
		public function set votenum(value: int): void{
			this._votenum = value;
		}
		
		/**
		 * get 倒计时（秒）
		 * @return 
		 */
		public function get ms(): int{
			return _ms;
		}
		
		/**
		 * set 倒计时（秒）
		 */
		public function set ms(value: int): void{
			this._ms = value;
		}
		
	}
}