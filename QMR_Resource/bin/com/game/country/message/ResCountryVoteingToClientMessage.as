package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 投票中票数变化
	 */
	public class ResCountryVoteingToClientMessage extends Message {
	
		//BUFFID
		private var _buffmodelid: Vector.<int> = new Vector.<int>();
		//票数
		private var _votenum: Vector.<int> = new Vector.<int>();
		//倒计时（秒）
		private var _ms: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//BUFFID
			writeShort(_buffmodelid.length);
			for (i = 0; i < _buffmodelid.length; i++) {
				writeInt(_buffmodelid[i]);
			}
			//票数
			writeShort(_votenum.length);
			for (i = 0; i < _votenum.length; i++) {
				writeInt(_votenum[i]);
			}
			//倒计时（秒）
			writeInt(_ms);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//BUFFID
			var buffmodelid_length : int = readShort();
			for (i = 0; i < buffmodelid_length; i++) {
				_buffmodelid[i] = readInt();
			}
			//票数
			var votenum_length : int = readShort();
			for (i = 0; i < votenum_length; i++) {
				_votenum[i] = readInt();
			}
			//倒计时（秒）
			_ms = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146115;
		}
		
		/**
		 * get BUFFID
		 * @return 
		 */
		public function get buffmodelid(): Vector.<int>{
			return _buffmodelid;
		}
		
		/**
		 * set BUFFID
		 */
		public function set buffmodelid(value: Vector.<int>): void{
			this._buffmodelid = value;
		}
		
		/**
		 * get 票数
		 * @return 
		 */
		public function get votenum(): Vector.<int>{
			return _votenum;
		}
		
		/**
		 * set 票数
		 */
		public function set votenum(value: Vector.<int>): void{
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