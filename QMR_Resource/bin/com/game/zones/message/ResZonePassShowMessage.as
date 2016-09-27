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
	 * 副本通关奖励展示（已经打乱的）
	 */
	public class ResZonePassShowMessage extends Message {
	
		//道具奖励列表
		private var _itemlist: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		//类型:0手动，1自动扫荡
		private var _type: int;
		
		//副本编号
		private var _zoneid: int;
		
		//死亡次数
		private var _deathnum: int;
		
		//通关时间
		private var _time: int;
		
		//杀怪数量
		private var _killmonstrnum: int;
		
		//最快通关时间（时间秒）
		private var _throughtime: int;
		
		//是否第一次，0不是，1是第一次
		private var _isfirst: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//道具奖励列表
			writeShort(_itemlist.length);
			for (i = 0; i < _itemlist.length; i++) {
				writeBean(_itemlist[i]);
			}
			//类型:0手动，1自动扫荡
			writeByte(_type);
			//副本编号
			writeInt(_zoneid);
			//死亡次数
			writeInt(_deathnum);
			//通关时间
			writeInt(_time);
			//杀怪数量
			writeInt(_killmonstrnum);
			//最快通关时间（时间秒）
			writeInt(_throughtime);
			//是否第一次，0不是，1是第一次
			writeInt(_isfirst);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//道具奖励列表
			var itemlist_length : int = readShort();
			for (i = 0; i < itemlist_length; i++) {
				_itemlist[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			//类型:0手动，1自动扫荡
			_type = readByte();
			//副本编号
			_zoneid = readInt();
			//死亡次数
			_deathnum = readInt();
			//通关时间
			_time = readInt();
			//杀怪数量
			_killmonstrnum = readInt();
			//最快通关时间（时间秒）
			_throughtime = readInt();
			//是否第一次，0不是，1是第一次
			_isfirst = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128103;
		}
		
		/**
		 * get 道具奖励列表
		 * @return 
		 */
		public function get itemlist(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _itemlist;
		}
		
		/**
		 * set 道具奖励列表
		 */
		public function set itemlist(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._itemlist = value;
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
		
		/**
		 * get 副本编号
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本编号
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 死亡次数
		 * @return 
		 */
		public function get deathnum(): int{
			return _deathnum;
		}
		
		/**
		 * set 死亡次数
		 */
		public function set deathnum(value: int): void{
			this._deathnum = value;
		}
		
		/**
		 * get 通关时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 通关时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 杀怪数量
		 * @return 
		 */
		public function get killmonstrnum(): int{
			return _killmonstrnum;
		}
		
		/**
		 * set 杀怪数量
		 */
		public function set killmonstrnum(value: int): void{
			this._killmonstrnum = value;
		}
		
		/**
		 * get 最快通关时间（时间秒）
		 * @return 
		 */
		public function get throughtime(): int{
			return _throughtime;
		}
		
		/**
		 * set 最快通关时间（时间秒）
		 */
		public function set throughtime(value: int): void{
			this._throughtime = value;
		}
		
		/**
		 * get 是否第一次，0不是，1是第一次
		 * @return 
		 */
		public function get isfirst(): int{
			return _isfirst;
		}
		
		/**
		 * set 是否第一次，0不是，1是第一次
		 */
		public function set isfirst(value: int): void{
			this._isfirst = value;
		}
		
	}
}