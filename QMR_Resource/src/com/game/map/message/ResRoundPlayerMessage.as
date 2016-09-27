package com.game.map.message{
	import com.game.map.bean.PlayerInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围玩家
	 */
	public class ResRoundPlayerMessage extends Message {
	
		//周围玩家信息
		private var _player: PlayerInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//周围玩家信息
			writeBean(_player);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//周围玩家信息
			_player = readBean(PlayerInfo) as PlayerInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101101;
		}
		
		/**
		 * get 周围玩家信息
		 * @return 
		 */
		public function get player(): PlayerInfo{
			return _player;
		}
		
		/**
		 * set 周围玩家信息
		 */
		public function set player(value: PlayerInfo): void{
			this._player = value;
		}
		
	}
}