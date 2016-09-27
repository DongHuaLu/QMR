package com.game.zones.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 4v4战场个人信息
	 */
	public class BfStructsInfo extends Bean {
	
		//玩家id
		private var _playerid: long;
		
		//玩家名字
		private var _playername: String;
		
		//玩家等级
		private var _playerlevel: int;
		
		//玩家阵营
		private var _camp: int;
		
		//死亡次数
		private var _deathnum: int;
		
		//杀敌次数
		private var _killnum: int;
		
		//累计得到经验
		private var _totalexp: int;
		
		//累计得到真气
		private var _totalzhenqi: int;
		
		//夺旗次数
		private var _seizeflag: int;
		
		//是否在线，1表示离线
		private var _online: int;
		
		//坐标X
		private var _x: int;
		
		//坐标Y
		private var _y: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家id
			writeLong(_playerid);
			//玩家名字
			writeString(_playername);
			//玩家等级
			writeInt(_playerlevel);
			//玩家阵营
			writeInt(_camp);
			//死亡次数
			writeInt(_deathnum);
			//杀敌次数
			writeInt(_killnum);
			//累计得到经验
			writeInt(_totalexp);
			//累计得到真气
			writeInt(_totalzhenqi);
			//夺旗次数
			writeInt(_seizeflag);
			//是否在线，1表示离线
			writeByte(_online);
			//坐标X
			writeShort(_x);
			//坐标Y
			writeShort(_y);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家id
			_playerid = readLong();
			//玩家名字
			_playername = readString();
			//玩家等级
			_playerlevel = readInt();
			//玩家阵营
			_camp = readInt();
			//死亡次数
			_deathnum = readInt();
			//杀敌次数
			_killnum = readInt();
			//累计得到经验
			_totalexp = readInt();
			//累计得到真气
			_totalzhenqi = readInt();
			//夺旗次数
			_seizeflag = readInt();
			//是否在线，1表示离线
			_online = readByte();
			//坐标X
			_x = readShort();
			//坐标Y
			_y = readShort();
			return true;
		}
		
		/**
		 * get 玩家id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家id
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
		 * get 玩家等级
		 * @return 
		 */
		public function get playerlevel(): int{
			return _playerlevel;
		}
		
		/**
		 * set 玩家等级
		 */
		public function set playerlevel(value: int): void{
			this._playerlevel = value;
		}
		
		/**
		 * get 玩家阵营
		 * @return 
		 */
		public function get camp(): int{
			return _camp;
		}
		
		/**
		 * set 玩家阵营
		 */
		public function set camp(value: int): void{
			this._camp = value;
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
		 * get 杀敌次数
		 * @return 
		 */
		public function get killnum(): int{
			return _killnum;
		}
		
		/**
		 * set 杀敌次数
		 */
		public function set killnum(value: int): void{
			this._killnum = value;
		}
		
		/**
		 * get 累计得到经验
		 * @return 
		 */
		public function get totalexp(): int{
			return _totalexp;
		}
		
		/**
		 * set 累计得到经验
		 */
		public function set totalexp(value: int): void{
			this._totalexp = value;
		}
		
		/**
		 * get 累计得到真气
		 * @return 
		 */
		public function get totalzhenqi(): int{
			return _totalzhenqi;
		}
		
		/**
		 * set 累计得到真气
		 */
		public function set totalzhenqi(value: int): void{
			this._totalzhenqi = value;
		}
		
		/**
		 * get 夺旗次数
		 * @return 
		 */
		public function get seizeflag(): int{
			return _seizeflag;
		}
		
		/**
		 * set 夺旗次数
		 */
		public function set seizeflag(value: int): void{
			this._seizeflag = value;
		}
		
		/**
		 * get 是否在线，1表示离线
		 * @return 
		 */
		public function get online(): int{
			return _online;
		}
		
		/**
		 * set 是否在线，1表示离线
		 */
		public function set online(value: int): void{
			this._online = value;
		}
		
		/**
		 * get 坐标X
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 坐标X
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 坐标Y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 坐标Y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
	}
}