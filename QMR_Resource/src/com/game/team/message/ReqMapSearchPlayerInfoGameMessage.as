package com.game.team.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 前端请求搜索本地图玩家信息，转发到世界服务器
	 */
	public class ReqMapSearchPlayerInfoGameMessage extends Message {
	
		//搜索内容
		private var _searchcontent: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//搜索内容
			writeString(_searchcontent);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//搜索内容
			_searchcontent = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118213;
		}
		
		/**
		 * get 搜索内容
		 * @return 
		 */
		public function get searchcontent(): String{
			return _searchcontent;
		}
		
		/**
		 * set 搜索内容
		 */
		public function set searchcontent(value: String): void{
			this._searchcontent = value;
		}
		
	}
}