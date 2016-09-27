package com.game.amulet.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送护身符升阶结果
	 */
	public class ResAmuletLevelUpMessage extends Message {
	
		//升阶结果，0失败，1成功
		private var _type: int;
		
		//当前祝福值
		private var _bless: int;
		
		//获得祝福值
		private var _gotbless: int;
		
		//获得经验
		private var _gotexp: int;
		
		//清空剩余时间
		private var _clearTime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//升阶结果，0失败，1成功
			writeByte(_type);
			//当前祝福值
			writeInt(_bless);
			//获得祝福值
			writeInt(_gotbless);
			//获得经验
			writeInt(_gotexp);
			//清空剩余时间
			writeInt(_clearTime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//升阶结果，0失败，1成功
			_type = readByte();
			//当前祝福值
			_bless = readInt();
			//获得祝福值
			_gotbless = readInt();
			//获得经验
			_gotexp = readInt();
			//清空剩余时间
			_clearTime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 166102;
		}
		
		/**
		 * get 升阶结果，0失败，1成功
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 升阶结果，0失败，1成功
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 当前祝福值
		 * @return 
		 */
		public function get bless(): int{
			return _bless;
		}
		
		/**
		 * set 当前祝福值
		 */
		public function set bless(value: int): void{
			this._bless = value;
		}
		
		/**
		 * get 获得祝福值
		 * @return 
		 */
		public function get gotbless(): int{
			return _gotbless;
		}
		
		/**
		 * set 获得祝福值
		 */
		public function set gotbless(value: int): void{
			this._gotbless = value;
		}
		
		/**
		 * get 获得经验
		 * @return 
		 */
		public function get gotexp(): int{
			return _gotexp;
		}
		
		/**
		 * set 获得经验
		 */
		public function set gotexp(value: int): void{
			this._gotexp = value;
		}
		
		/**
		 * get 清空剩余时间
		 * @return 
		 */
		public function get clearTime(): int{
			return _clearTime;
		}
		
		/**
		 * set 清空剩余时间
		 */
		public function set clearTime(value: int): void{
			this._clearTime = value;
		}
		
	}
}