package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送升级技能CD时间
	 */
	public class ReshorseCDTimeMessage extends Message {
	
		//拉杆CD时间
		private var _num: int;
		
		//清除CD需要的元宝数量
		private var _cdtimeyuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//拉杆CD时间
			writeInt(_num);
			//清除CD需要的元宝数量
			writeInt(_cdtimeyuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//拉杆CD时间
			_num = readInt();
			//清除CD需要的元宝数量
			_cdtimeyuanbao = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126111;
		}
		
		/**
		 * get 拉杆CD时间
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 拉杆CD时间
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 清除CD需要的元宝数量
		 * @return 
		 */
		public function get cdtimeyuanbao(): int{
			return _cdtimeyuanbao;
		}
		
		/**
		 * set 清除CD需要的元宝数量
		 */
		public function set cdtimeyuanbao(value: int): void{
			this._cdtimeyuanbao = value;
		}
		
	}
}