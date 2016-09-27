package com.game.transactions.bean{
	import com.game.utils.long;
	import com.game.backpack.bean.ItemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 临时元宝日志信息
	 */
	public class TmpYuanbaoLogInfo extends Bean {
	
		//和本人交易的玩家ID
		private var _playerid: long;
		
		//玩家名字
		private var _playername: String;
		
		//交易类型
		private var _type: int;
		
		//交易时间
		private var _time: int;
		
		//道具信息
		private var _iteminfo: com.game.backpack.bean.ItemInfo;
		
		//交易的道具数量
		private var _num: int;
		
		//获得金币数量
		private var _goldnum: int;
		
		//获得元宝数量
		private var _yuanbaonum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//和本人交易的玩家ID
			writeLong(_playerid);
			//玩家名字
			writeString(_playername);
			//交易类型
			writeByte(_type);
			//交易时间
			writeInt(_time);
			//道具信息
			writeBean(_iteminfo);
			//交易的道具数量
			writeInt(_num);
			//获得金币数量
			writeInt(_goldnum);
			//获得元宝数量
			writeInt(_yuanbaonum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//和本人交易的玩家ID
			_playerid = readLong();
			//玩家名字
			_playername = readString();
			//交易类型
			_type = readByte();
			//交易时间
			_time = readInt();
			//道具信息
			_iteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			//交易的道具数量
			_num = readInt();
			//获得金币数量
			_goldnum = readInt();
			//获得元宝数量
			_yuanbaonum = readInt();
			return true;
		}
		
		/**
		 * get 和本人交易的玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 和本人交易的玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 玩家名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 玩家名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 交易类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 交易类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 交易时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 交易时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 道具信息
		 * @return 
		 */
		public function get iteminfo(): com.game.backpack.bean.ItemInfo{
			return _iteminfo;
		}
		
		/**
		 * set 道具信息
		 */
		public function set iteminfo(value: com.game.backpack.bean.ItemInfo): void{
			this._iteminfo = value;
		}
		
		/**
		 * get 交易的道具数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 交易的道具数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 获得金币数量
		 * @return 
		 */
		public function get goldnum(): int{
			return _goldnum;
		}
		
		/**
		 * set 获得金币数量
		 */
		public function set goldnum(value: int): void{
			this._goldnum = value;
		}
		
		/**
		 * get 获得元宝数量
		 * @return 
		 */
		public function get yuanbaonum(): int{
			return _yuanbaonum;
		}
		
		/**
		 * set 获得元宝数量
		 */
		public function set yuanbaonum(value: int): void{
			this._yuanbaonum = value;
		}
		
	}
}