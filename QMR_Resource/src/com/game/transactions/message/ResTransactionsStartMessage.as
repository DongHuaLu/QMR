package com.game.transactions.message{
	import com.game.transactions.bean.RoleModeInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * B玩家同意，开始交易
	 */
	public class ResTransactionsStartMessage extends Message {
	
		//A交易玩家造型信息
		private var _arolemodeinfo: RoleModeInfo;
		
		//B交易玩家造型信息
		private var _brolemodeinfo: RoleModeInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//A交易玩家造型信息
			writeBean(_arolemodeinfo);
			//B交易玩家造型信息
			writeBean(_brolemodeinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//A交易玩家造型信息
			_arolemodeinfo = readBean(RoleModeInfo) as RoleModeInfo;
			//B交易玩家造型信息
			_brolemodeinfo = readBean(RoleModeInfo) as RoleModeInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122103;
		}
		
		/**
		 * get A交易玩家造型信息
		 * @return 
		 */
		public function get arolemodeinfo(): RoleModeInfo{
			return _arolemodeinfo;
		}
		
		/**
		 * set A交易玩家造型信息
		 */
		public function set arolemodeinfo(value: RoleModeInfo): void{
			this._arolemodeinfo = value;
		}
		
		/**
		 * get B交易玩家造型信息
		 * @return 
		 */
		public function get brolemodeinfo(): RoleModeInfo{
			return _brolemodeinfo;
		}
		
		/**
		 * set B交易玩家造型信息
		 */
		public function set brolemodeinfo(value: RoleModeInfo): void{
			this._brolemodeinfo = value;
		}
		
	}
}