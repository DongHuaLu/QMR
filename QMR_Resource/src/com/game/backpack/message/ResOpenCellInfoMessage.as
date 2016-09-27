package com.game.backpack.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开格子所需条件以及奖励信息
	 */
	public class ResOpenCellInfoMessage extends Message {
	
		//格子编号
		private var _cellId: int;
		
		//剩余时间(秒)
		private var _timeRemaining: int;
		
		//所需元宝数
		private var _gold: int;
		
		//开启后获得的经验数
		private var _exp: int;
		
		//增大的最大血量
		private var _maxhp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号
			writeInt(_cellId);
			//剩余时间(秒)
			writeInt(_timeRemaining);
			//所需元宝数
			writeInt(_gold);
			//开启后获得的经验数
			writeInt(_exp);
			//增大的最大血量
			writeInt(_maxhp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号
			_cellId = readInt();
			//剩余时间(秒)
			_timeRemaining = readInt();
			//所需元宝数
			_gold = readInt();
			//开启后获得的经验数
			_exp = readInt();
			//增大的最大血量
			_maxhp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104108;
		}
		
		/**
		 * get 格子编号
		 * @return 
		 */
		public function get cellId(): int{
			return _cellId;
		}
		
		/**
		 * set 格子编号
		 */
		public function set cellId(value: int): void{
			this._cellId = value;
		}
		
		/**
		 * get 剩余时间(秒)
		 * @return 
		 */
		public function get timeRemaining(): int{
			return _timeRemaining;
		}
		
		/**
		 * set 剩余时间(秒)
		 */
		public function set timeRemaining(value: int): void{
			this._timeRemaining = value;
		}
		
		/**
		 * get 所需元宝数
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 所需元宝数
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
		/**
		 * get 开启后获得的经验数
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 开启后获得的经验数
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 增大的最大血量
		 * @return 
		 */
		public function get maxhp(): int{
			return _maxhp;
		}
		
		/**
		 * set 增大的最大血量
		 */
		public function set maxhp(value: int): void{
			this._maxhp = value;
		}
		
	}
}