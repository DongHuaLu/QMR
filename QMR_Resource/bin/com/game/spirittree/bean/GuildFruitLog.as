package com.game.spirittree.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 帮会神树操作日志
	 */
	public class GuildFruitLog extends Bean {
	
		//时间（秒）
		private var _time: int;
		
		//操作的组包ID（果实名称）
		private var _groupid: int;
		
		//类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
		private var _type: int;
		
		//浇水得到经验 或者 被偷补偿奖励
		private var _exp: int;
		
		//对方ID
		private var _otherid: long;
		
		//对方名字
		private var _othername: String;
		
		//是否在线，0不在线，1在线
		private var _isonline: int;
		
		//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
		private var _itemmodelid: int;
		
		//偷取的数量（ID为经验才需要显示）
		private var _itemnum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//时间（秒）
			writeInt(_time);
			//操作的组包ID（果实名称）
			writeInt(_groupid);
			//类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
			writeByte(_type);
			//浇水得到经验 或者 被偷补偿奖励
			writeInt(_exp);
			//对方ID
			writeLong(_otherid);
			//对方名字
			writeString(_othername);
			//是否在线，0不在线，1在线
			writeByte(_isonline);
			//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
			writeInt(_itemmodelid);
			//偷取的数量（ID为经验才需要显示）
			writeInt(_itemnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//时间（秒）
			_time = readInt();
			//操作的组包ID（果实名称）
			_groupid = readInt();
			//类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
			_type = readByte();
			//浇水得到经验 或者 被偷补偿奖励
			_exp = readInt();
			//对方ID
			_otherid = readLong();
			//对方名字
			_othername = readString();
			//是否在线，0不在线，1在线
			_isonline = readByte();
			//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
			_itemmodelid = readInt();
			//偷取的数量（ID为经验才需要显示）
			_itemnum = readInt();
			return true;
		}
		
		/**
		 * get 时间（秒）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 时间（秒）
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 操作的组包ID（果实名称）
		 * @return 
		 */
		public function get groupid(): int{
			return _groupid;
		}
		
		/**
		 * set 操作的组包ID（果实名称）
		 */
		public function set groupid(value: int): void{
			this._groupid = value;
		}
		
		/**
		 * get 类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 浇水得到经验 或者 被偷补偿奖励
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 浇水得到经验 或者 被偷补偿奖励
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 对方ID
		 * @return 
		 */
		public function get otherid(): long{
			return _otherid;
		}
		
		/**
		 * set 对方ID
		 */
		public function set otherid(value: long): void{
			this._otherid = value;
		}
		
		/**
		 * get 对方名字
		 * @return 
		 */
		public function get othername(): String{
			return _othername;
		}
		
		/**
		 * set 对方名字
		 */
		public function set othername(value: String): void{
			this._othername = value;
		}
		
		/**
		 * get 是否在线，0不在线，1在线
		 * @return 
		 */
		public function get isonline(): int{
			return _isonline;
		}
		
		/**
		 * set 是否在线，0不在线，1在线
		 */
		public function set isonline(value: int): void{
			this._isonline = value;
		}
		
		/**
		 * get 最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
		 * @return 
		 */
		public function get itemmodelid(): int{
			return _itemmodelid;
		}
		
		/**
		 * set 最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
		 */
		public function set itemmodelid(value: int): void{
			this._itemmodelid = value;
		}
		
		/**
		 * get 偷取的数量（ID为经验才需要显示）
		 * @return 
		 */
		public function get itemnum(): int{
			return _itemnum;
		}
		
		/**
		 * set 偷取的数量（ID为经验才需要显示）
		 */
		public function set itemnum(value: int): void{
			this._itemnum = value;
		}
		
	}
}