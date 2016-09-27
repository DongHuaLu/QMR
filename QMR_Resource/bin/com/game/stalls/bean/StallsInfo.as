package com.game.stalls.bean{
	import com.game.utils.long;
	import com.game.stalls.bean.StallsGoodsInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摊位信息（发送前端）
	 */
	public class StallsInfo extends Bean {
	
		//玩家名字
		private var _playername: String;
		
		//玩家ID
		private var _playerid: long;
		
		//摊位等级，0，1，2，3
		private var _stallslv: int;
		
		//摊位名字
		private var _stallsname: String;
		
		//摊位物品信息列表
		private var _stallsgoodsinfo: Vector.<StallsGoodsInfo> = new Vector.<StallsGoodsInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家名字
			writeString(_playername);
			//玩家ID
			writeLong(_playerid);
			//摊位等级，0，1，2，3
			writeByte(_stallslv);
			//摊位名字
			writeString(_stallsname);
			//摊位物品信息列表
			writeShort(_stallsgoodsinfo.length);
			for (var i: int = 0; i < _stallsgoodsinfo.length; i++) {
				writeBean(_stallsgoodsinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家名字
			_playername = readString();
			//玩家ID
			_playerid = readLong();
			//摊位等级，0，1，2，3
			_stallslv = readByte();
			//摊位名字
			_stallsname = readString();
			//摊位物品信息列表
			var stallsgoodsinfo_length : int = readShort();
			for (var i: int = 0; i < stallsgoodsinfo_length; i++) {
				_stallsgoodsinfo[i] = readBean(StallsGoodsInfo) as StallsGoodsInfo;
			}
			return true;
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
		 * get 玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 摊位等级，0，1，2，3
		 * @return 
		 */
		public function get stallslv(): int{
			return _stallslv;
		}
		
		/**
		 * set 摊位等级，0，1，2，3
		 */
		public function set stallslv(value: int): void{
			this._stallslv = value;
		}
		
		/**
		 * get 摊位名字
		 * @return 
		 */
		public function get stallsname(): String{
			return _stallsname;
		}
		
		/**
		 * set 摊位名字
		 */
		public function set stallsname(value: String): void{
			this._stallsname = value;
		}
		
		/**
		 * get 摊位物品信息列表
		 * @return 
		 */
		public function get stallsgoodsinfo(): Vector.<StallsGoodsInfo>{
			return _stallsgoodsinfo;
		}
		
		/**
		 * set 摊位物品信息列表
		 */
		public function set stallsgoodsinfo(value: Vector.<StallsGoodsInfo>): void{
			this._stallsgoodsinfo = value;
		}
		
	}
}