package com.game.toplist.message{
	import com.game.toplist.bean.TopInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送给前端玩家信息,支持离线
	 */
	public class ResToplistPlayerInfoMessage extends Message {
	
		//玩家信息
		private var _playerInfo: TopInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家信息
			writeBean(_playerInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家信息
			_playerInfo = readBean(TopInfo) as TopInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142105;
		}
		
		/**
		 * get 玩家信息
		 * @return 
		 */
		public function get playerInfo(): TopInfo{
			return _playerInfo;
		}
		
		/**
		 * set 玩家信息
		 */
		public function set playerInfo(value: TopInfo): void{
			this._playerInfo = value;
		}
		
	}
}