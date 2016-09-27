package com.game.cloak.bean{
	import com.game.cloak.bean.CloakStoneInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 披风信息
	 */
	public class CloakInfo extends Bean {
	
		//披风model
		private var _model: int;
		
		//剩余过期时间
		private var _time: int;
		
		//当前祝福值
		private var _bless: int;
		
		//清空剩余时间
		private var _clearTime: int;
		
		//镶嵌信息
		private var _stones: Vector.<CloakStoneInfo> = new Vector.<CloakStoneInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//披风model
			writeInt(_model);
			//剩余过期时间
			writeInt(_time);
			//当前祝福值
			writeInt(_bless);
			//清空剩余时间
			writeInt(_clearTime);
			//镶嵌信息
			writeShort(_stones.length);
			for (var i: int = 0; i < _stones.length; i++) {
				writeBean(_stones[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//披风model
			_model = readInt();
			//剩余过期时间
			_time = readInt();
			//当前祝福值
			_bless = readInt();
			//清空剩余时间
			_clearTime = readInt();
			//镶嵌信息
			var stones_length : int = readShort();
			for (var i: int = 0; i < stones_length; i++) {
				_stones[i] = readBean(CloakStoneInfo) as CloakStoneInfo;
			}
			return true;
		}
		
		/**
		 * get 披风model
		 * @return 
		 */
		public function get model(): int{
			return _model;
		}
		
		/**
		 * set 披风model
		 */
		public function set model(value: int): void{
			this._model = value;
		}
		
		/**
		 * get 剩余过期时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 剩余过期时间
		 */
		public function set time(value: int): void{
			this._time = value;
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
		
		/**
		 * get 镶嵌信息
		 * @return 
		 */
		public function get stones(): Vector.<CloakStoneInfo>{
			return _stones;
		}
		
		/**
		 * set 镶嵌信息
		 */
		public function set stones(value: Vector.<CloakStoneInfo>): void{
			this._stones = value;
		}
		
	}
}