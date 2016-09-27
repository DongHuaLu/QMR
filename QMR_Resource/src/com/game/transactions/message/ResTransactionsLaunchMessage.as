package com.game.transactions.message{
	import com.game.transactions.bean.RoleModeInfo;
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发给B玩家交易请求
	 */
	public class ResTransactionsLaunchMessage extends Message {
	
		//A交易玩家造型信息
		private var _rolemodeinfo: RoleModeInfo;
		
		//发起交易时间
		private var _launchtime: int;
		
		//交易ID
		private var _transid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//A交易玩家造型信息
			writeBean(_rolemodeinfo);
			//发起交易时间
			writeInt(_launchtime);
			//交易ID
			writeLong(_transid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//A交易玩家造型信息
			_rolemodeinfo = readBean(RoleModeInfo) as RoleModeInfo;
			//发起交易时间
			_launchtime = readInt();
			//交易ID
			_transid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122101;
		}
		
		/**
		 * get A交易玩家造型信息
		 * @return 
		 */
		public function get rolemodeinfo(): RoleModeInfo{
			return _rolemodeinfo;
		}
		
		/**
		 * set A交易玩家造型信息
		 */
		public function set rolemodeinfo(value: RoleModeInfo): void{
			this._rolemodeinfo = value;
		}
		
		/**
		 * get 发起交易时间
		 * @return 
		 */
		public function get launchtime(): int{
			return _launchtime;
		}
		
		/**
		 * set 发起交易时间
		 */
		public function set launchtime(value: int): void{
			this._launchtime = value;
		}
		
		/**
		 * get 交易ID
		 * @return 
		 */
		public function get transid(): long{
			return _transid;
		}
		
		/**
		 * set 交易ID
		 */
		public function set transid(value: long): void{
			this._transid = value;
		}
		
	}
}