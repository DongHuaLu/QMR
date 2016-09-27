package com.game.classicbattle.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 禁地令牌不足弹出面板
	 */
	public class ResClassicBattleInfoTokenLackToClientMessage extends Message {
	
		//道具ID
		private var _itemmodelid: int;
		
		//缺少的数量
		private var _lacknum: int;
		
		//需要的总数量
		private var _sum: int;
		
		//当前层数
		private var _currlayers: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具ID
			writeInt(_itemmodelid);
			//缺少的数量
			writeInt(_lacknum);
			//需要的总数量
			writeInt(_sum);
			//当前层数
			writeInt(_currlayers);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具ID
			_itemmodelid = readInt();
			//缺少的数量
			_lacknum = readInt();
			//需要的总数量
			_sum = readInt();
			//当前层数
			_currlayers = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 165102;
		}
		
		/**
		 * get 道具ID
		 * @return 
		 */
		public function get itemmodelid(): int{
			return _itemmodelid;
		}
		
		/**
		 * set 道具ID
		 */
		public function set itemmodelid(value: int): void{
			this._itemmodelid = value;
		}
		
		/**
		 * get 缺少的数量
		 * @return 
		 */
		public function get lacknum(): int{
			return _lacknum;
		}
		
		/**
		 * set 缺少的数量
		 */
		public function set lacknum(value: int): void{
			this._lacknum = value;
		}
		
		/**
		 * get 需要的总数量
		 * @return 
		 */
		public function get sum(): int{
			return _sum;
		}
		
		/**
		 * set 需要的总数量
		 */
		public function set sum(value: int): void{
			this._sum = value;
		}
		
		/**
		 * get 当前层数
		 * @return 
		 */
		public function get currlayers(): int{
			return _currlayers;
		}
		
		/**
		 * set 当前层数
		 */
		public function set currlayers(value: int): void{
			this._currlayers = value;
		}
		
	}
}