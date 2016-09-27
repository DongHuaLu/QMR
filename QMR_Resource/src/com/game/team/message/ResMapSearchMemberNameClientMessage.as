package com.game.team.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 前端请求队员名字列表
	 */
	public class ResMapSearchMemberNameClientMessage extends Message {
	
		//队员名字列表
		private var _membernamelist: Vector.<String> = new Vector.<String>();
		//队员等级列表
		private var _membernamelv: Vector.<int> = new Vector.<int>();
		//队员线路列表
		private var _membernameline: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//队员名字列表
			writeShort(_membernamelist.length);
			for (i = 0; i < _membernamelist.length; i++) {
				writeString(_membernamelist[i]);
			}
			//队员等级列表
			writeShort(_membernamelv.length);
			for (i = 0; i < _membernamelv.length; i++) {
				writeShort(_membernamelv[i]);
			}
			//队员线路列表
			writeShort(_membernameline.length);
			for (i = 0; i < _membernameline.length; i++) {
				writeByte(_membernameline[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//队员名字列表
			var membernamelist_length : int = readShort();
			for (i = 0; i < membernamelist_length; i++) {
				_membernamelist[i] = readString();
			}
			//队员等级列表
			var membernamelv_length : int = readShort();
			for (i = 0; i < membernamelv_length; i++) {
				_membernamelv[i] = readShort();
			}
			//队员线路列表
			var membernameline_length : int = readShort();
			for (i = 0; i < membernameline_length; i++) {
				_membernameline[i] = readByte();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118110;
		}
		
		/**
		 * get 队员名字列表
		 * @return 
		 */
		public function get membernamelist(): Vector.<String>{
			return _membernamelist;
		}
		
		/**
		 * set 队员名字列表
		 */
		public function set membernamelist(value: Vector.<String>): void{
			this._membernamelist = value;
		}
		
		/**
		 * get 队员等级列表
		 * @return 
		 */
		public function get membernamelv(): Vector.<int>{
			return _membernamelv;
		}
		
		/**
		 * set 队员等级列表
		 */
		public function set membernamelv(value: Vector.<int>): void{
			this._membernamelv = value;
		}
		
		/**
		 * get 队员线路列表
		 * @return 
		 */
		public function get membernameline(): Vector.<int>{
			return _membernameline;
		}
		
		/**
		 * set 队员线路列表
		 */
		public function set membernameline(value: Vector.<int>): void{
			this._membernameline = value;
		}
		
	}
}