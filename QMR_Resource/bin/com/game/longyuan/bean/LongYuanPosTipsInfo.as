package com.game.longyuan.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 龙元心法星位Tips信息
	 */
	public class LongYuanPosTipsInfo extends Bean {
	
		//当前的龙元心法阶段（星图）
		private var _longyuanactlv: int;
		
		//当前的龙元心法星位
		private var _longyuanactnum: int;
		
		//成功率
		private var _successrate: int;
		
		//龙元心法分享经验人数
		private var _longyuanexpshare: int;
		
		//龙元心法分享经验总量
		private var _longyuanshareexpsum: int;
		
		//是否已经激活
		private var _isachieve: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前的龙元心法阶段（星图）
			writeByte(_longyuanactlv);
			//当前的龙元心法星位
			writeByte(_longyuanactnum);
			//成功率
			writeInt(_successrate);
			//龙元心法分享经验人数
			writeShort(_longyuanexpshare);
			//龙元心法分享经验总量
			writeInt(_longyuanshareexpsum);
			//是否已经激活
			writeByte(_isachieve);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前的龙元心法阶段（星图）
			_longyuanactlv = readByte();
			//当前的龙元心法星位
			_longyuanactnum = readByte();
			//成功率
			_successrate = readInt();
			//龙元心法分享经验人数
			_longyuanexpshare = readShort();
			//龙元心法分享经验总量
			_longyuanshareexpsum = readInt();
			//是否已经激活
			_isachieve = readByte();
			return true;
		}
		
		/**
		 * get 当前的龙元心法阶段（星图）
		 * @return 
		 */
		public function get longyuanactlv(): int{
			return _longyuanactlv;
		}
		
		/**
		 * set 当前的龙元心法阶段（星图）
		 */
		public function set longyuanactlv(value: int): void{
			this._longyuanactlv = value;
		}
		
		/**
		 * get 当前的龙元心法星位
		 * @return 
		 */
		public function get longyuanactnum(): int{
			return _longyuanactnum;
		}
		
		/**
		 * set 当前的龙元心法星位
		 */
		public function set longyuanactnum(value: int): void{
			this._longyuanactnum = value;
		}
		
		/**
		 * get 成功率
		 * @return 
		 */
		public function get successrate(): int{
			return _successrate;
		}
		
		/**
		 * set 成功率
		 */
		public function set successrate(value: int): void{
			this._successrate = value;
		}
		
		/**
		 * get 龙元心法分享经验人数
		 * @return 
		 */
		public function get longyuanexpshare(): int{
			return _longyuanexpshare;
		}
		
		/**
		 * set 龙元心法分享经验人数
		 */
		public function set longyuanexpshare(value: int): void{
			this._longyuanexpshare = value;
		}
		
		/**
		 * get 龙元心法分享经验总量
		 * @return 
		 */
		public function get longyuanshareexpsum(): int{
			return _longyuanshareexpsum;
		}
		
		/**
		 * set 龙元心法分享经验总量
		 */
		public function set longyuanshareexpsum(value: int): void{
			this._longyuanshareexpsum = value;
		}
		
		/**
		 * get 是否已经激活
		 * @return 
		 */
		public function get isachieve(): int{
			return _isachieve;
		}
		
		/**
		 * set 是否已经激活
		 */
		public function set isachieve(value: int): void{
			this._isachieve = value;
		}
		
	}
}