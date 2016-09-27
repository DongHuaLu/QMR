package com.game.spirittree.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 奖励道具简要信息（连续催熟时用）
	 */
	public class Rewardbriefinfo extends Bean {
	
		//道具ID
		private var _itemmodelid: int;
		
		//道具数量
		private var _itemnum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具ID
			writeInt(_itemmodelid);
			//道具数量
			writeInt(_itemnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具ID
			_itemmodelid = readInt();
			//道具数量
			_itemnum = readInt();
			return true;
		}
		
		/**
		 * get 道具ID
		 * @return 
		 */
		public function get itemmodelid(): int{
			return _itemmodelid;
		}
		
		/**
		 * set 道具ID
		 */
		public function set itemmodelid(value: int): void{
			this._itemmodelid = value;
		}
		
		/**
		 * get 道具数量
		 * @return 
		 */
		public function get itemnum(): int{
			return _itemnum;
		}
		
		/**
		 * set 道具数量
		 */
		public function set itemnum(value: int): void{
			this._itemnum = value;
		}
		
	}
}