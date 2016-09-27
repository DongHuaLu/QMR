package com.game.player.message{
	import com.game.player.bean.OtherPlayerInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 他人玩家信息
	 */
	public class ResOtherPlayerInfoMessage extends Message {
	
		//他人玩家信息
		private var _otherPlayerInfo: OtherPlayerInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//他人玩家信息
			writeBean(_otherPlayerInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//他人玩家信息
			_otherPlayerInfo = readBean(OtherPlayerInfo) as OtherPlayerInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103108;
		}
		
		/**
		 * get 他人玩家信息
		 * @return 
		 */
		public function get otherPlayerInfo(): OtherPlayerInfo{
			return _otherPlayerInfo;
		}
		
		/**
		 * set 他人玩家信息
		 */
		public function set otherPlayerInfo(value: OtherPlayerInfo): void{
			this._otherPlayerInfo = value;
		}
		
	}
}