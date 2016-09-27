package com.game.country.message{
	import com.game.country.bean.WarRewardInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 在线时间奖励
	 */
	public class ResKingCityTimeRewardToClientMessage extends Message {
	
		//在线奖励
		private var _warrewardinfo: WarRewardInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//在线奖励
			writeBean(_warrewardinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//在线奖励
			_warrewardinfo = readBean(WarRewardInfo) as WarRewardInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146110;
		}
		
		/**
		 * get 在线奖励
		 * @return 
		 */
		public function get warrewardinfo(): WarRewardInfo{
			return _warrewardinfo;
		}
		
		/**
		 * set 在线奖励
		 */
		public function set warrewardinfo(value: WarRewardInfo): void{
			this._warrewardinfo = value;
		}
		
	}
}