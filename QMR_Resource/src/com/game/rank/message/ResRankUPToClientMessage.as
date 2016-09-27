package com.game.rank.message{
	import com.game.utils.long;
	import com.game.rank.bean.Rankinfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军衔改变广播
	 */
	public class ResRankUPToClientMessage extends Message {
	
		//玩家ID
		private var _playerid: long;
		
		//军衔保存信息
		private var _rankinfo: Rankinfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_playerid);
			//军衔保存信息
			writeBean(_rankinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_playerid = readLong();
			//军衔保存信息
			_rankinfo = readBean(Rankinfo) as Rankinfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 117103;
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
		 * get 军衔保存信息
		 * @return 
		 */
		public function get rankinfo(): Rankinfo{
			return _rankinfo;
		}
		
		/**
		 * set 军衔保存信息
		 */
		public function set rankinfo(value: Rankinfo): void{
			this._rankinfo = value;
		}
		
	}
}