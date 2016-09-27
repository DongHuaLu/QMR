package com.game.zones.message{
	import com.game.zones.bean.ZoneMonstrInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 副本内过关计时器（进入副本后开始）
	 */
	public class ResZoneTimerMessage extends Message {
	
		//副本编号
		private var _zoneid: int;
		
		//怪物总数
		private var _monstrssum: int;
		
		//当前怪物数量
		private var _monstrsnum: int;
		
		//死亡次数
		private var _deathnum: int;
		
		//剩余时间
		private var _surplustime: int;
		
		//面板开关，0结束时关闭，1开启
		private var _status: int;
		
		//副本怪物信息列表
		private var _zoenmonstrinfolist: Vector.<ZoneMonstrInfo> = new Vector.<ZoneMonstrInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//副本编号
			writeInt(_zoneid);
			//怪物总数
			writeInt(_monstrssum);
			//当前怪物数量
			writeInt(_monstrsnum);
			//死亡次数
			writeInt(_deathnum);
			//剩余时间
			writeInt(_surplustime);
			//面板开关，0结束时关闭，1开启
			writeByte(_status);
			//副本怪物信息列表
			writeShort(_zoenmonstrinfolist.length);
			for (i = 0; i < _zoenmonstrinfolist.length; i++) {
				writeBean(_zoenmonstrinfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//副本编号
			_zoneid = readInt();
			//怪物总数
			_monstrssum = readInt();
			//当前怪物数量
			_monstrsnum = readInt();
			//死亡次数
			_deathnum = readInt();
			//剩余时间
			_surplustime = readInt();
			//面板开关，0结束时关闭，1开启
			_status = readByte();
			//副本怪物信息列表
			var zoenmonstrinfolist_length : int = readShort();
			for (i = 0; i < zoenmonstrinfolist_length; i++) {
				_zoenmonstrinfolist[i] = readBean(ZoneMonstrInfo) as ZoneMonstrInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128102;
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
		 * get 怪物总数
		 * @return 
		 */
		public function get monstrssum(): int{
			return _monstrssum;
		}
		
		/**
		 * set 怪物总数
		 */
		public function set monstrssum(value: int): void{
			this._monstrssum = value;
		}
		
		/**
		 * get 当前怪物数量
		 * @return 
		 */
		public function get monstrsnum(): int{
			return _monstrsnum;
		}
		
		/**
		 * set 当前怪物数量
		 */
		public function set monstrsnum(value: int): void{
			this._monstrsnum = value;
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
		 * get 剩余时间
		 * @return 
		 */
		public function get surplustime(): int{
			return _surplustime;
		}
		
		/**
		 * set 剩余时间
		 */
		public function set surplustime(value: int): void{
			this._surplustime = value;
		}
		
		/**
		 * get 面板开关，0结束时关闭，1开启
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 面板开关，0结束时关闭，1开启
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
		/**
		 * get 副本怪物信息列表
		 * @return 
		 */
		public function get zoenmonstrinfolist(): Vector.<ZoneMonstrInfo>{
			return _zoenmonstrinfolist;
		}
		
		/**
		 * set 副本怪物信息列表
		 */
		public function set zoenmonstrinfolist(value: Vector.<ZoneMonstrInfo>): void{
			this._zoenmonstrinfolist = value;
		}
		
	}
}