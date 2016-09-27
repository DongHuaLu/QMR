package com.game.stalls.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摊位搜索
	 */
	public class ReqStallsSearchMessage extends Message {
	
		//道具名称
		private var _goodsname: String;
		
		//玩家名字
		private var _playername: String;
		
		//搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
		private var _goldyuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具名称
			writeString(_goodsname);
			//玩家名字
			writeString(_playername);
			//搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
			writeByte(_goldyuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具名称
			_goodsname = readString();
			//玩家名字
			_playername = readString();
			//搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
			_goldyuanbao = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123208;
		}
		
		/**
		 * get 道具名称
		 * @return 
		 */
		public function get goodsname(): String{
			return _goodsname;
		}
		
		/**
		 * set 道具名称
		 */
		public function set goodsname(value: String): void{
			this._goodsname = value;
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
		 * get 搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
		 * @return 
		 */
		public function get goldyuanbao(): int{
			return _goldyuanbao;
		}
		
		/**
		 * set 搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
		 */
		public function set goldyuanbao(value: int): void{
			this._goldyuanbao = value;
		}
		
	}
}