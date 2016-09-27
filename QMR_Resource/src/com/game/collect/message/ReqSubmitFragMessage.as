package com.game.collect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 提交碎片根据物品
	 */
	public class ReqSubmitFragMessage extends Message {
	
		//碎片
		private var _itemmodel: int;
		
		//数量
		private var _num: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//碎片
			writeInt(_itemmodel);
			//数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//碎片
			_itemmodel = readInt();
			//数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 153202;
		}
		
		/**
		 * get 碎片
		 * @return 
		 */
		public function get itemmodel(): int{
			return _itemmodel;
		}
		
		/**
		 * set 碎片
		 */
		public function set itemmodel(value: int): void{
			this._itemmodel = value;
		}
		
		/**
		 * get 数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}