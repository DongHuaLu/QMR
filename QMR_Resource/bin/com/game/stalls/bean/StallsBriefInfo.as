package com.game.stalls.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 单个摊位简要信息
	 */
	public class StallsBriefInfo extends Bean {
	
		//玩家名字
		private var _playername: String;
		
		//玩家ID
		private var _playerid: long;
		
		//玩家等级
		private var _playerlv: int;
		
		//摊位号（只用于排序）
		private var _stallsid: int;
		
		//摊位等级，0，1，2，3
		private var _stallslv: int;
		
		//摊位名字(如果为空，前端自行组合)
		private var _stallsname: String;
		
		//出售金币数量
		private var _sellgold: int;
		
		//出售元宝数量
		private var _sellyuanbao: int;
		
		//出售道具数量
		private var _sellgoodsnum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家名字
			writeString(_playername);
			//玩家ID
			writeLong(_playerid);
			//玩家等级
			writeShort(_playerlv);
			//摊位号（只用于排序）
			writeInt(_stallsid);
			//摊位等级，0，1，2，3
			writeByte(_stallslv);
			//摊位名字(如果为空，前端自行组合)
			writeString(_stallsname);
			//出售金币数量
			writeInt(_sellgold);
			//出售元宝数量
			writeInt(_sellyuanbao);
			//出售道具数量
			writeByte(_sellgoodsnum);
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
			//玩家等级
			_playerlv = readShort();
			//摊位号（只用于排序）
			_stallsid = readInt();
			//摊位等级，0，1，2，3
			_stallslv = readByte();
			//摊位名字(如果为空，前端自行组合)
			_stallsname = readString();
			//出售金币数量
			_sellgold = readInt();
			//出售元宝数量
			_sellyuanbao = readInt();
			//出售道具数量
			_sellgoodsnum = readByte();
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
		 * get 玩家等级
		 * @return 
		 */
		public function get playerlv(): int{
			return _playerlv;
		}
		
		/**
		 * set 玩家等级
		 */
		public function set playerlv(value: int): void{
			this._playerlv = value;
		}
		
		/**
		 * get 摊位号（只用于排序）
		 * @return 
		 */
		public function get stallsid(): int{
			return _stallsid;
		}
		
		/**
		 * set 摊位号（只用于排序）
		 */
		public function set stallsid(value: int): void{
			this._stallsid = value;
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
		 * get 摊位名字(如果为空，前端自行组合)
		 * @return 
		 */
		public function get stallsname(): String{
			return _stallsname;
		}
		
		/**
		 * set 摊位名字(如果为空，前端自行组合)
		 */
		public function set stallsname(value: String): void{
			this._stallsname = value;
		}
		
		/**
		 * get 出售金币数量
		 * @return 
		 */
		public function get sellgold(): int{
			return _sellgold;
		}
		
		/**
		 * set 出售金币数量
		 */
		public function set sellgold(value: int): void{
			this._sellgold = value;
		}
		
		/**
		 * get 出售元宝数量
		 * @return 
		 */
		public function get sellyuanbao(): int{
			return _sellyuanbao;
		}
		
		/**
		 * set 出售元宝数量
		 */
		public function set sellyuanbao(value: int): void{
			this._sellyuanbao = value;
		}
		
		/**
		 * get 出售道具数量
		 * @return 
		 */
		public function get sellgoodsnum(): int{
			return _sellgoodsnum;
		}
		
		/**
		 * set 出售道具数量
		 */
		public function set sellgoodsnum(value: int): void{
			this._sellgoodsnum = value;
		}
		
	}
}