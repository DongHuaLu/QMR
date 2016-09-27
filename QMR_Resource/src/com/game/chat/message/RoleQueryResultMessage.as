package com.game.chat.message{
	import com.game.chat.bean.RoleChatInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 查找结果列表
	 */
	public class RoleQueryResultMessage extends Message {
	
		//页码
		private var _page: int;
		
		//总数
		private var _count: int;
		
		//结果列表
		private var _list: Vector.<RoleChatInfo> = new Vector.<RoleChatInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//页码
			writeInt(_page);
			//总数
			writeInt(_count);
			//结果列表
			writeShort(_list.length);
			for (i = 0; i < _list.length; i++) {
				writeBean(_list[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//页码
			_page = readInt();
			//总数
			_count = readInt();
			//结果列表
			var list_length : int = readShort();
			for (i = 0; i < list_length; i++) {
				_list[i] = readBean(RoleChatInfo) as RoleChatInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 111102;
		}
		
		/**
		 * get 页码
		 * @return 
		 */
		public function get page(): int{
			return _page;
		}
		
		/**
		 * set 页码
		 */
		public function set page(value: int): void{
			this._page = value;
		}
		
		/**
		 * get 总数
		 * @return 
		 */
		public function get count(): int{
			return _count;
		}
		
		/**
		 * set 总数
		 */
		public function set count(value: int): void{
			this._count = value;
		}
		
		/**
		 * get 结果列表
		 * @return 
		 */
		public function get list(): Vector.<RoleChatInfo>{
			return _list;
		}
		
		/**
		 * set 结果列表
		 */
		public function set list(value: Vector.<RoleChatInfo>): void{
			this._list = value;
		}
		
	}
}