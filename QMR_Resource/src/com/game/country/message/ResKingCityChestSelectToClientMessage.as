package com.game.country.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 王城宝箱奖励选择
	 */
	public class ResKingCityChestSelectToClientMessage extends Message {
	
		//选择的道具奖励信息
		private var _iteminfo: com.game.backpack.bean.ItemInfo;
		
		//选择次数
		private var _num: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//选择的道具奖励信息
			writeBean(_iteminfo);
			//选择次数
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//选择的道具奖励信息
			_iteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			//选择次数
			_num = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146109;
		}
		
		/**
		 * get 选择的道具奖励信息
		 * @return 
		 */
		public function get iteminfo(): com.game.backpack.bean.ItemInfo{
			return _iteminfo;
		}
		
		/**
		 * set 选择的道具奖励信息
		 */
		public function set iteminfo(value: com.game.backpack.bean.ItemInfo): void{
			this._iteminfo = value;
		}
		
		/**
		 * get 选择次数
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 选择次数
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}