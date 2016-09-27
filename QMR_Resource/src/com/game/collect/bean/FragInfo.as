package com.game.collect.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 碎片信息
	 */
	public class FragInfo extends Bean {
	
		//物品模型
		private var _modelid: int;
		
		//当前数量
		private var _num: int;
		
		//所需数量
		private var _neednum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品模型
			writeInt(_modelid);
			//当前数量
			writeInt(_num);
			//所需数量
			writeInt(_neednum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品模型
			_modelid = readInt();
			//当前数量
			_num = readInt();
			//所需数量
			_neednum = readInt();
			return true;
		}
		
		/**
		 * get 物品模型
		 * @return 
		 */
		public function get modelid(): int{
			return _modelid;
		}
		
		/**
		 * set 物品模型
		 */
		public function set modelid(value: int): void{
			this._modelid = value;
		}
		
		/**
		 * get 当前数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 当前数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 所需数量
		 * @return 
		 */
		public function get neednum(): int{
			return _neednum;
		}
		
		/**
		 * set 所需数量
		 */
		public function set neednum(value: int): void{
			this._neednum = value;
		}
		
	}
}