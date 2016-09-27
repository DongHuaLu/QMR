package com.game.biwudao.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送比武岛区域更新
	 */
	public class ResBiWuDaoAreadoubleToClientMessage extends Message {
	
		//区域经验倍率
		private var _areadouble: int;
		
		//每次可获得经验值
		private var _availableexp: int;
		
		//每次可获得真气值
		private var _availablezhenqi: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//区域经验倍率
			writeInt(_areadouble);
			//每次可获得经验值
			writeInt(_availableexp);
			//每次可获得真气值
			writeInt(_availablezhenqi);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//区域经验倍率
			_areadouble = readInt();
			//每次可获得经验值
			_availableexp = readInt();
			//每次可获得真气值
			_availablezhenqi = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157102;
		}
		
		/**
		 * get 区域经验倍率
		 * @return 
		 */
		public function get areadouble(): int{
			return _areadouble;
		}
		
		/**
		 * set 区域经验倍率
		 */
		public function set areadouble(value: int): void{
			this._areadouble = value;
		}
		
		/**
		 * get 每次可获得经验值
		 * @return 
		 */
		public function get availableexp(): int{
			return _availableexp;
		}
		
		/**
		 * set 每次可获得经验值
		 */
		public function set availableexp(value: int): void{
			this._availableexp = value;
		}
		
		/**
		 * get 每次可获得真气值
		 * @return 
		 */
		public function get availablezhenqi(): int{
			return _availablezhenqi;
		}
		
		/**
		 * set 每次可获得真气值
		 */
		public function set availablezhenqi(value: int): void{
			this._availablezhenqi = value;
		}
		
	}
}