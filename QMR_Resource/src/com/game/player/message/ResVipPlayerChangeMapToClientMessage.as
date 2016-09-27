package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送VIP玩家改变地图消息
	 */
	public class ResVipPlayerChangeMapToClientMessage extends Message {
	
		//当前次数
		private var _curnum: int;
		
		//最大次数
		private var _maxnum: int;
		
		//VIP等级
		private var _viplv: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前次数
			writeInt(_curnum);
			//最大次数
			writeInt(_maxnum);
			//VIP等级
			writeInt(_viplv);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前次数
			_curnum = readInt();
			//最大次数
			_maxnum = readInt();
			//VIP等级
			_viplv = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103132;
		}
		
		/**
		 * get 当前次数
		 * @return 
		 */
		public function get curnum(): int{
			return _curnum;
		}
		
		/**
		 * set 当前次数
		 */
		public function set curnum(value: int): void{
			this._curnum = value;
		}
		
		/**
		 * get 最大次数
		 * @return 
		 */
		public function get maxnum(): int{
			return _maxnum;
		}
		
		/**
		 * set 最大次数
		 */
		public function set maxnum(value: int): void{
			this._maxnum = value;
		}
		
		/**
		 * get VIP等级
		 * @return 
		 */
		public function get viplv(): int{
			return _viplv;
		}
		
		/**
		 * set VIP等级
		 */
		public function set viplv(value: int): void{
			this._viplv = value;
		}
		
	}
}