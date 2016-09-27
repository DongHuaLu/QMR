package com.game.dataserver.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 战场个人信息
	 */
	public class BfPlayerInfo extends Bean {
	
		//玩家ID
		private var _playerId: long;
		
		//连胜次数
		private var _winningstreak: int;
		
		//最近场次胜负记录
		private var _recvord: int;
		
		//勋章
		private var _medal: int;
		
		//荣誉值
		private var _honor: int;
		
		//战斗力
		private var _fightpower: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_playerId);
			//连胜次数
			writeInt(_winningstreak);
			//最近场次胜负记录
			writeInt(_recvord);
			//勋章
			writeInt(_medal);
			//荣誉值
			writeInt(_honor);
			//战斗力
			writeInt(_fightpower);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_playerId = readLong();
			//连胜次数
			_winningstreak = readInt();
			//最近场次胜负记录
			_recvord = readInt();
			//勋章
			_medal = readInt();
			//荣誉值
			_honor = readInt();
			//战斗力
			_fightpower = readInt();
			return true;
		}
		
		/**
		 * get 玩家ID
		 * @return 
		 */
		public function get playerId(): long{
			return _playerId;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set playerId(value: long): void{
			this._playerId = value;
		}
		
		/**
		 * get 连胜次数
		 * @return 
		 */
		public function get winningstreak(): int{
			return _winningstreak;
		}
		
		/**
		 * set 连胜次数
		 */
		public function set winningstreak(value: int): void{
			this._winningstreak = value;
		}
		
		/**
		 * get 最近场次胜负记录
		 * @return 
		 */
		public function get recvord(): int{
			return _recvord;
		}
		
		/**
		 * set 最近场次胜负记录
		 */
		public function set recvord(value: int): void{
			this._recvord = value;
		}
		
		/**
		 * get 勋章
		 * @return 
		 */
		public function get medal(): int{
			return _medal;
		}
		
		/**
		 * set 勋章
		 */
		public function set medal(value: int): void{
			this._medal = value;
		}
		
		/**
		 * get 荣誉值
		 * @return 
		 */
		public function get honor(): int{
			return _honor;
		}
		
		/**
		 * set 荣誉值
		 */
		public function set honor(value: int): void{
			this._honor = value;
		}
		
		/**
		 * get 战斗力
		 * @return 
		 */
		public function get fightpower(): int{
			return _fightpower;
		}
		
		/**
		 * set 战斗力
		 */
		public function set fightpower(value: int): void{
			this._fightpower = value;
		}
		
	}
}