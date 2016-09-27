package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回配偶龙元心法
	 */
	public class ResGetSpouseLongYuanToClientMessage extends Message {
	
		//龙元心法阶段（星图）
		private var _longyuanlv: int;
		
		//龙元心法星位
		private var _longyuannum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
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
			//龙元心法阶段（星图）
			_longyuanlv = readByte();
			//龙元心法星位
			_longyuannum = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163116;
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