package com.game.toplist.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 排行宝箱信息
	 */
	public class ChestInfo extends Bean {
	
		//宝箱id(0-最后一个宝箱)
		private var _chestid: int;
		
		//宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
		private var _chesttype: int;
		
		//是否可领取(0-不可领  1-可领)
		private var _canreceive: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宝箱id(0-最后一个宝箱)
			writeInt(_chestid);
			//宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
			writeInt(_chesttype);
			//是否可领取(0-不可领  1-可领)
			writeByte(_canreceive);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宝箱id(0-最后一个宝箱)
			_chestid = readInt();
			//宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
			_chesttype = readInt();
			//是否可领取(0-不可领  1-可领)
			_canreceive = readByte();
			return true;
		}
		
		/**
		 * get 宝箱id(0-最后一个宝箱)
		 * @return 
		 */
		public function get chestid(): int{
			return _chestid;
		}
		
		/**
		 * set 宝箱id(0-最后一个宝箱)
		 */
		public function set chestid(value: int): void{
			this._chestid = value;
		}
		
		/**
		 * get 宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
		 * @return 
		 */
		public function get chesttype(): int{
			return _chesttype;
		}
		
		/**
		 * set 宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
		 */
		public function set chesttype(value: int): void{
			this._chesttype = value;
		}
		
		/**
		 * get 是否可领取(0-不可领  1-可领)
		 * @return 
		 */
		public function get canreceive(): int{
			return _canreceive;
		}
		
		/**
		 * set 是否可领取(0-不可领  1-可领)
		 */
		public function set canreceive(value: int): void{
			this._canreceive = value;
		}
		
	}
}