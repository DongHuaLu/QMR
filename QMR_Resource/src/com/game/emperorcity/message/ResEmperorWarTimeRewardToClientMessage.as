package com.game.emperorcity.message{
	import com.game.emperorcity.bean.EmperorWarRewardInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 在线时间奖励信息
	 */
	public class ResEmperorWarTimeRewardToClientMessage extends Message {
	
		//在线奖励
		private var _emperorWarRewardInfo: EmperorWarRewardInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//在线奖励
			writeBean(_emperorWarRewardInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//在线奖励
			_emperorWarRewardInfo = readBean(EmperorWarRewardInfo) as EmperorWarRewardInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169106;
		}
		
		/**
		 * get 在线奖励
		 * @return 
		 */
		public function get emperorWarRewardInfo(): EmperorWarRewardInfo{
			return _emperorWarRewardInfo;
		}
		
		/**
		 * set 在线奖励
		 */
		public function set emperorWarRewardInfo(value: EmperorWarRewardInfo): void{
			this._emperorWarRewardInfo = value;
		}
		
	}
}