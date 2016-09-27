package com.game.buff.bean{
	import com.game.utils.long;
	import com.game.buff.bean.BuffPara;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * buff信息
	 */
	public class BuffInfo extends Bean {
	
		//Buff Id
		private var _buffId: long;
		
		//Buff 模板Id
		private var _modelId: int;
		
		//Buff 总时间
		private var _total: int;
		
		//Buff 剩余时间
		private var _remain: int;
		
		//Buff 叠加层数
		private var _overlay: int;
		
		//Buff 数值(血池时是血量)
		private var _value: int;
		
		//Buff 比例
		private var _percent: int;
		
		//buff 参数
		private var _buffparas: Vector.<BuffPara> = new Vector.<BuffPara>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//Buff Id
			writeLong(_buffId);
			//Buff 模板Id
			writeInt(_modelId);
			//Buff 总时间
			writeInt(_total);
			//Buff 剩余时间
			writeInt(_remain);
			//Buff 叠加层数
			writeInt(_overlay);
			//Buff 数值(血池时是血量)
			writeInt(_value);
			//Buff 比例
			writeInt(_percent);
			//buff 参数
			writeShort(_buffparas.length);
			for (var i: int = 0; i < _buffparas.length; i++) {
				writeBean(_buffparas[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//Buff Id
			_buffId = readLong();
			//Buff 模板Id
			_modelId = readInt();
			//Buff 总时间
			_total = readInt();
			//Buff 剩余时间
			_remain = readInt();
			//Buff 叠加层数
			_overlay = readInt();
			//Buff 数值(血池时是血量)
			_value = readInt();
			//Buff 比例
			_percent = readInt();
			//buff 参数
			var buffparas_length : int = readShort();
			for (var i: int = 0; i < buffparas_length; i++) {
				_buffparas[i] = readBean(BuffPara) as BuffPara;
			}
			return true;
		}
		
		/**
		 * get Buff Id
		 * @return 
		 */
		public function get buffId(): long{
			return _buffId;
		}
		
		/**
		 * set Buff Id
		 */
		public function set buffId(value: long): void{
			this._buffId = value;
		}
		
		/**
		 * get Buff 模板Id
		 * @return 
		 */
		public function get modelId(): int{
			return _modelId;
		}
		
		/**
		 * set Buff 模板Id
		 */
		public function set modelId(value: int): void{
			this._modelId = value;
		}
		
		/**
		 * get Buff 总时间
		 * @return 
		 */
		public function get total(): int{
			return _total;
		}
		
		/**
		 * set Buff 总时间
		 */
		public function set total(value: int): void{
			this._total = value;
		}
		
		/**
		 * get Buff 剩余时间
		 * @return 
		 */
		public function get remain(): int{
			return _remain;
		}
		
		/**
		 * set Buff 剩余时间
		 */
		public function set remain(value: int): void{
			this._remain = value;
		}
		
		/**
		 * get Buff 叠加层数
		 * @return 
		 */
		public function get overlay(): int{
			return _overlay;
		}
		
		/**
		 * set Buff 叠加层数
		 */
		public function set overlay(value: int): void{
			this._overlay = value;
		}
		
		/**
		 * get Buff 数值(血池时是血量)
		 * @return 
		 */
		public function get value(): int{
			return _value;
		}
		
		/**
		 * set Buff 数值(血池时是血量)
		 */
		public function set value(value: int): void{
			this._value = value;
		}
		
		/**
		 * get Buff 比例
		 * @return 
		 */
		public function get percent(): int{
			return _percent;
		}
		
		/**
		 * set Buff 比例
		 */
		public function set percent(value: int): void{
			this._percent = value;
		}
		
		/**
		 * get buff 参数
		 * @return 
		 */
		public function get buffparas(): Vector.<BuffPara>{
			return _buffparas;
		}
		
		/**
		 * set buff 参数
		 */
		public function set buffparas(value: Vector.<BuffPara>): void{
			this._buffparas = value;
		}
		
	}
}