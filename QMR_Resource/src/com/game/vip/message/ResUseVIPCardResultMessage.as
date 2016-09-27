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
	 * 返回VIP卡使用结果
	 */
	public class ResUseVIPCardResultMessage extends Message {
	
		//VIP卡使用是否成功
		private var _usesuccess: int;
		
		//玩家VIP信息
		private var _vipinfo: VipInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//VIP卡使用是否成功
			writeByte(_usesuccess);
			//玩家VIP信息
			writeBean(_vipinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//VIP卡使用是否成功
			_usesuccess = readByte();
			//玩家VIP信息
			_vipinfo = readBean(VipInfo) as VipInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 147102;
		}
		
		/**
		 * get VIP卡使用是否成功
		 * @return 
		 */
		public function get usesuccess(): int{
			return _usesuccess;
		}
		
		/**
		 * set VIP卡使用是否成功
		 */
		public function set usesuccess(value: int): void{
			this._usesuccess = value;
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