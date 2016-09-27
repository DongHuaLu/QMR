package com.game.dazuo.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 使用凝丹，灵泉露不足，弹出面板
	 */
	public class ResCohesionZhenQiInadequateMessage extends Message {
	
		//玩家名字
		private var _playername: String;
		
		//今日使用他人的真气凝丹次数
		private var _usenum: int;
		
		//灵泉露缺少数量
		private var _lacknum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家名字
			writeString(_playername);
			//今日使用他人的真气凝丹次数
			writeInt(_usenum);
			//灵泉露缺少数量
			writeInt(_lacknum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家名字
			_playername = readString();
			//今日使用他人的真气凝丹次数
			_usenum = readInt();
			//灵泉露缺少数量
			_lacknum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136105;
		}
		
		/**
		 * get 玩家名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 玩家名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 今日使用他人的真气凝丹次数
		 * @return 
		 */
		public function get usenum(): int{
			return _usenum;
		}
		
		/**
		 * set 今日使用他人的真气凝丹次数
		 */
		public function set usenum(value: int): void{
			this._usenum = value;
		}
		
		/**
		 * get 灵泉露缺少数量
		 * @return 
		 */
		public function get lacknum(): int{
			return _lacknum;
		}
		
		/**
		 * set 灵泉露缺少数量
		 */
		public function set lacknum(value: int): void{
			this._lacknum = value;
		}
		
	}
}