package com.game.vip.message{
	import com.game.vip.bean.VipInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回玩家VIP信息
	 */
	public class ResPlayerVIPInfoMessage extends Message {
	
		//玩家VIP信息
		private var _vipinfo: VipInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家VIP信息
			writeBean(_vipinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家VIP信息
			_vipinfo = readBean(VipInfo) as VipInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 147101;
		}
		
		/**
		 * get 玩家VIP信息
		 * @return 
		 */
		public function get vipinfo(): VipInfo{
			return _vipinfo;
		}
		
		/**
		 * set 玩家VIP信息
		 */
		public function set vipinfo(value: VipInfo): void{
			this._vipinfo = value;
		}
		
	}
}