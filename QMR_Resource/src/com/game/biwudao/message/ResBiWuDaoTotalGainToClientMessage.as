package com.game.biwudao.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送比武岛累计收益
	 */
	public class ResBiWuDaoTotalGainToClientMessage extends Message {
	
		//累计获得经验
		private var _totalexp: int;
		
		//累计获得真气
		private var _totalzhenqi: int;
		
		//累计获得军功
		private var _totalrank: int;
		
		//累计开启宝箱
		private var _totalBox: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//累计获得经验
			writeInt(_totalexp);
			//累计获得真气
			writeInt(_totalzhenqi);
			//累计获得军功
			writeInt(_totalrank);
			//累计开启宝箱
			writeInt(_totalBox);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//累计获得经验
			_totalexp = readInt();
			//累计获得真气
			_totalzhenqi = readInt();
			//累计获得军功
			_totalrank = readInt();
			//累计开启宝箱
			_totalBox = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157103;
		}
		
		/**
		 * get 累计获得经验
		 * @return 
		 */
		public function get totalexp(): int{
			return _totalexp;
		}
		
		/**
		 * set 累计获得经验
		 */
		public function set totalexp(value: int): void{
			this._totalexp = value;
		}
		
		/**
		 * get 累计获得真气
		 * @return 
		 */
		public function get totalzhenqi(): int{
			return _totalzhenqi;
		}
		
		/**
		 * set 累计获得真气
		 */
		public function set totalzhenqi(value: int): void{
			this._totalzhenqi = value;
		}
		
		/**
		 * get 累计获得军功
		 * @return 
		 */
		public function get totalrank(): int{
			return _totalrank;
		}
		
		/**
		 * set 累计获得军功
		 */
		public function set totalrank(value: int): void{
			this._totalrank = value;
		}
		
		/**
		 * get 累计开启宝箱
		 * @return 
		 */
		public function get totalBox(): int{
			return _totalBox;
		}
		
		/**
		 * set 累计开启宝箱
		 */
		public function set totalBox(value: int): void{
			this._totalBox = value;
		}
		
	}
}