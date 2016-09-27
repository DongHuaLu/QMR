package com.game.spirittree.bean{
	import com.game.utils.long;
	import com.game.spirittree.bean.FruitRewardinfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 果实信息
	 */
	public class FruitInfo extends Bean {
	
		//果实ID
		private var _fruitid: long;
		
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		private var _type: int;
		
		//果实成熟剩余时间(秒)，-1表示成熟
		private var _timeleft: int;
		
		//是否干旱，0否，1是
		private var _isarid: int;
		
		//组包ID列表
		private var _groupidlist: Vector.<int> = new Vector.<int>();
		//果实奖励
		private var _fruitrewardinfo: Vector.<FruitRewardinfo> = new Vector.<FruitRewardinfo>();
		//剩余产量
		private var _yield: int;
		
		//果实主人ID
		private var _hostid: long;
		
		//果实主人名字
		private var _hostname: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//果实ID
			writeLong(_fruitid);
			//果实类型：0普通果实，1银色奇异果，2金色奇异果
			writeByte(_type);
			//果实成熟剩余时间(秒)，-1表示成熟
			writeInt(_timeleft);
			//是否干旱，0否，1是
			writeByte(_isarid);
			//组包ID列表
			writeShort(_groupidlist.length);
			for (var i: int = 0; i < _groupidlist.length; i++) {
				writeInt(_groupidlist[i]);
			}
			//果实奖励
			writeShort(_fruitrewardinfo.length);
			for (var i: int = 0; i < _fruitrewardinfo.length; i++) {
				writeBean(_fruitrewardinfo[i]);
			}
			//剩余产量
			writeInt(_yield);
			//果实主人ID
			writeLong(_hostid);
			//果实主人名字
			writeString(_hostname);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//果实ID
			_fruitid = readLong();
			//果实类型：0普通果实，1银色奇异果，2金色奇异果
			_type = readByte();
			//果实成熟剩余时间(秒)，-1表示成熟
			_timeleft = readInt();
			//是否干旱，0否，1是
			_isarid = readByte();
			//组包ID列表
			var groupidlist_length : int = readShort();
			for (var i: int = 0; i < groupidlist_length; i++) {
				_groupidlist[i] = readInt();
			}
			//果实奖励
			var fruitrewardinfo_length : int = readShort();
			for (var i: int = 0; i < fruitrewardinfo_length; i++) {
				_fruitrewardinfo[i] = readBean(FruitRewardinfo) as FruitRewardinfo;
			}
			//剩余产量
			_yield = readInt();
			//果实主人ID
			_hostid = readLong();
			//果实主人名字
			_hostname = readString();
			return true;
		}
		
		/**
		 * get 果实ID
		 * @return 
		 */
		public function get fruitid(): long{
			return _fruitid;
		}
		
		/**
		 * set 果实ID
		 */
		public function set fruitid(value: long): void{
			this._fruitid = value;
		}
		
		/**
		 * get 果实类型：0普通果实，1银色奇异果，2金色奇异果
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 果实类型：0普通果实，1银色奇异果，2金色奇异果
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 果实成熟剩余时间(秒)，-1表示成熟
		 * @return 
		 */
		public function get timeleft(): int{
			return _timeleft;
		}
		
		/**
		 * set 果实成熟剩余时间(秒)，-1表示成熟
		 */
		public function set timeleft(value: int): void{
			this._timeleft = value;
		}
		
		/**
		 * get 是否干旱，0否，1是
		 * @return 
		 */
		public function get isarid(): int{
			return _isarid;
		}
		
		/**
		 * set 是否干旱，0否，1是
		 */
		public function set isarid(value: int): void{
			this._isarid = value;
		}
		
		/**
		 * get 组包ID列表
		 * @return 
		 */
		public function get groupidlist(): Vector.<int>{
			return _groupidlist;
		}
		
		/**
		 * set 组包ID列表
		 */
		public function set groupidlist(value: Vector.<int>): void{
			this._groupidlist = value;
		}
		
		/**
		 * get 果实奖励
		 * @return 
		 */
		public function get fruitrewardinfo(): Vector.<FruitRewardinfo>{
			return _fruitrewardinfo;
		}
		
		/**
		 * set 果实奖励
		 */
		public function set fruitrewardinfo(value: Vector.<FruitRewardinfo>): void{
			this._fruitrewardinfo = value;
		}
		
		/**
		 * get 剩余产量
		 * @return 
		 */
		public function get yield(): int{
			return _yield;
		}
		
		/**
		 * set 剩余产量
		 */
		public function set yield(value: int): void{
			this._yield = value;
		}
		
		/**
		 * get 果实主人ID
		 * @return 
		 */
		public function get hostid(): long{
			return _hostid;
		}
		
		/**
		 * set 果实主人ID
		 */
		public function set hostid(value: long): void{
			this._hostid = value;
		}
		
		/**
		 * get 果实主人名字
		 * @return 
		 */
		public function get hostname(): String{
			return _hostname;
		}
		
		/**
		 * set 果实主人名字
		 */
		public function set hostname(value: String): void{
			this._hostname = value;
		}
		
	}
}