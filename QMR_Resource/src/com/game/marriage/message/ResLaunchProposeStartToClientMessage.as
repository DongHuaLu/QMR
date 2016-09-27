package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 求婚信息展示
	 */
	public class ResLaunchProposeStartToClientMessage extends Message {
	
		//求婚发起人ID
		private var _playerid: long;
		
		//求婚发起人名字
		private var _playername: String;
		
		//求婚发起人等级
		private var _playerlv: int;
		
		//求婚发起人帮会名字
		private var _guildname: String;
		
		//戒指模版ID
		private var _ringmodelid: int;
		
		//求婚发起人头像
		private var _avatarid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//求婚发起人ID
			writeLong(_playerid);
			//求婚发起人名字
			writeString(_playername);
			//求婚发起人等级
			writeShort(_playerlv);
			//求婚发起人帮会名字
			writeString(_guildname);
			//戒指模版ID
			writeInt(_ringmodelid);
			//求婚发起人头像
			writeInt(_avatarid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//求婚发起人ID
			_playerid = readLong();
			//求婚发起人名字
			_playername = readString();
			//求婚发起人等级
			_playerlv = readShort();
			//求婚发起人帮会名字
			_guildname = readString();
			//戒指模版ID
			_ringmodelid = readInt();
			//求婚发起人头像
			_avatarid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163102;
		}
		
		/**
		 * get 求婚发起人ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 求婚发起人ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 求婚发起人名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 求婚发起人名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 求婚发起人等级
		 * @return 
		 */
		public function get playerlv(): int{
			return _playerlv;
		}
		
		/**
		 * set 求婚发起人等级
		 */
		public function set playerlv(value: int): void{
			this._playerlv = value;
		}
		
		/**
		 * get 求婚发起人帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 求婚发起人帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 戒指模版ID
		 * @return 
		 */
		public function get ringmodelid(): int{
			return _ringmodelid;
		}
		
		/**
		 * set 戒指模版ID
		 */
		public function set ringmodelid(value: int): void{
			this._ringmodelid = value;
		}
		
		/**
		 * get 求婚发起人头像
		 * @return 
		 */
		public function get avatarid(): int{
			return _avatarid;
		}
		
		/**
		 * set 求婚发起人头像
		 */
		public function set avatarid(value: int): void{
			this._avatarid = value;
		}
		
	}
}