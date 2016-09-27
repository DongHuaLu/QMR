package com.game.zones.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 翻牌-副本通关奖励信息
	 */
	public class ResZoneSelectAwardMessage extends Message {
	
		//单个道具奖励列表
		private var _iteminfo: com.game.backpack.bean.ItemInfo;
		
		//选择次数
		private var _num: int;
		
		//类型:0手动，1自动扫荡
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//单个道具奖励列表
			writeBean(_iteminfo);
			//选择次数
			writeByte(_num);
			//类型:0手动，1自动扫荡
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//单个道具奖励列表
			_iteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			//选择次数
			_num = readByte();
			//类型:0手动，1自动扫荡
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128104;
		}
		
		/**
		 * get 单个道具奖励列表
		 * @return 
		 */
		public function get iteminfo(): com.game.backpack.bean.ItemInfo{
			return _iteminfo;
		}
		
		/**
		 * set 单个道具奖励列表
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
		
		/**
		 * get 类型:0手动，1自动扫荡
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型:0手动，1自动扫荡
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}