package com.game.player.message{
	import com.game.utils.long;
	import com.game.player.bean.PlayerAppearanceInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取玩家造型信息
	 */
	public class ResGetPlayerAppearanceInfMessage extends Message {
	
		//他人ID
		private var _othersid: long;
		
		//面板类型
		private var _type: int;
		
		//外观信息
		private var _appearanceInfo: PlayerAppearanceInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//他人ID
			writeLong(_othersid);
			//面板类型
			writeByte(_type);
			//外观信息
			writeBean(_appearanceInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//他人ID
			_othersid = readLong();
			//面板类型
			_type = readByte();
			//外观信息
			_appearanceInfo = readBean(PlayerAppearanceInfo) as PlayerAppearanceInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103126;
		}
		
		/**
		 * get 他人ID
		 * @return 
		 */
		public function get othersid(): long{
			return _othersid;
		}
		
		/**
		 * set 他人ID
		 */
		public function set othersid(value: long): void{
			this._othersid = value;
		}
		
		/**
		 * get 面板类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 面板类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 外观信息
		 * @return 
		 */
		public function get appearanceInfo(): PlayerAppearanceInfo{
			return _appearanceInfo;
		}
		
		/**
		 * set 外观信息
		 */
		public function set appearanceInfo(value: PlayerAppearanceInfo): void{
			this._appearanceInfo = value;
		}
		
	}
}