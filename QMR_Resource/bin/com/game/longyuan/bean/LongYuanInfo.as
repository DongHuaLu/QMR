package com.game.longyuan.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 龙元心法信息
	 */
	public class LongYuanInfo extends Bean {
	
		//龙元心法分享经验人数
		private var _longyuanexpshare: int;
		
		//龙元心法阶段（星图）
		private var _longyuanlv: int;
		
		//龙元心法星位
		private var _longyuannum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//龙元心法分享经验人数
			writeShort(_longyuanexpshare);
			//龙元心法阶段（星图）
			writeByte(_longyuanlv);
			//龙元心法星位
			writeByte(_longyuannum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//龙元心法分享经验人数
			_longyuanexpshare = readShort();
			//龙元心法阶段（星图）
			_longyuanlv = readByte();
			//龙元心法星位
			_longyuannum = readByte();
			return true;
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
		 * get 龙元心法阶段（星图）
		 * @return 
		 */
		public function get longyuanlv(): int{
			return _longyuanlv;
		}
		
		/**
		 * set 龙元心法阶段（星图）
		 */
		public function set longyuanlv(value: int): void{
			this._longyuanlv = value;
		}
		
		/**
		 * get 龙元心法星位
		 * @return 
		 */
		public function get longyuannum(): int{
			return _longyuannum;
		}
		
		/**
		 * set 龙元心法星位
		 */
		public function set longyuannum(value: int): void{
			this._longyuannum = value;
		}
		
	}
}