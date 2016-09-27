package com.game.dazuo.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打座收益
	 */
	public class ResDazuoEruptMessage extends Message {
	
		//打座持续时间
		private var _duration: int;
		
		//打坐获得经验
		private var _dazuoexp: int;
		
		//打坐获得真气
		private var _dazuozq: int;
		
		//暴击次数
		private var _eruptCount: int;
		
		//暴击获得经验
		private var _eruptExp: int;
		
		//暴击获得真气
		private var _eruptZQ: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//打座持续时间
			writeInt(_duration);
			//打坐获得经验
			writeInt(_dazuoexp);
			//打坐获得真气
			writeInt(_dazuozq);
			//暴击次数
			writeInt(_eruptCount);
			//暴击获得经验
			writeInt(_eruptExp);
			//暴击获得真气
			writeInt(_eruptZQ);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//打座持续时间
			_duration = readInt();
			//打坐获得经验
			_dazuoexp = readInt();
			//打坐获得真气
			_dazuozq = readInt();
			//暴击次数
			_eruptCount = readInt();
			//暴击获得经验
			_eruptExp = readInt();
			//暴击获得真气
			_eruptZQ = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136103;
		}
		
		/**
		 * get 打座持续时间
		 * @return 
		 */
		public function get duration(): int{
			return _duration;
		}
		
		/**
		 * set 打座持续时间
		 */
		public function set duration(value: int): void{
			this._duration = value;
		}
		
		/**
		 * get 打坐获得经验
		 * @return 
		 */
		public function get dazuoexp(): int{
			return _dazuoexp;
		}
		
		/**
		 * set 打坐获得经验
		 */
		public function set dazuoexp(value: int): void{
			this._dazuoexp = value;
		}
		
		/**
		 * get 打坐获得真气
		 * @return 
		 */
		public function get dazuozq(): int{
			return _dazuozq;
		}
		
		/**
		 * set 打坐获得真气
		 */
		public function set dazuozq(value: int): void{
			this._dazuozq = value;
		}
		
		/**
		 * get 暴击次数
		 * @return 
		 */
		public function get eruptCount(): int{
			return _eruptCount;
		}
		
		/**
		 * set 暴击次数
		 */
		public function set eruptCount(value: int): void{
			this._eruptCount = value;
		}
		
		/**
		 * get 暴击获得经验
		 * @return 
		 */
		public function get eruptExp(): int{
			return _eruptExp;
		}
		
		/**
		 * set 暴击获得经验
		 */
		public function set eruptExp(value: int): void{
			this._eruptExp = value;
		}
		
		/**
		 * get 暴击获得真气
		 * @return 
		 */
		public function get eruptZQ(): int{
			return _eruptZQ;
		}
		
		/**
		 * set 暴击获得真气
		 */
		public function set eruptZQ(value: int): void{
			this._eruptZQ = value;
		}
		
	}
}