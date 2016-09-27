package com.game.transactions.message{
	import com.game.transactions.bean.TmpYuanbaoLogInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送临时元宝日志
	 */
	public class ResTmpYuanbaoLogMessage extends Message {
	
		//汇率
		private var _exchange: String;
		
		//网址
		private var _web: String;
		
		//可领取元宝
		private var _canryuanbao: int;
		
		//临时元宝日志列表
		private var _tpmyuanbaolonginfo: Vector.<TmpYuanbaoLogInfo> = new Vector.<TmpYuanbaoLogInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//汇率
			writeString(_exchange);
			//网址
			writeString(_web);
			//可领取元宝
			writeInt(_canryuanbao);
			//临时元宝日志列表
			writeShort(_tpmyuanbaolonginfo.length);
			for (i = 0; i < _tpmyuanbaolonginfo.length; i++) {
				writeBean(_tpmyuanbaolonginfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//汇率
			_exchange = readString();
			//网址
			_web = readString();
			//可领取元宝
			_canryuanbao = readInt();
			//临时元宝日志列表
			var tpmyuanbaolonginfo_length : int = readShort();
			for (i = 0; i < tpmyuanbaolonginfo_length; i++) {
				_tpmyuanbaolonginfo[i] = readBean(TmpYuanbaoLogInfo) as TmpYuanbaoLogInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122112;
		}
		
		/**
		 * get 汇率
		 * @return 
		 */
		public function get exchange(): String{
			return _exchange;
		}
		
		/**
		 * set 汇率
		 */
		public function set exchange(value: String): void{
			this._exchange = value;
		}
		
		/**
		 * get 网址
		 * @return 
		 */
		public function get web(): String{
			return _web;
		}
		
		/**
		 * set 网址
		 */
		public function set web(value: String): void{
			this._web = value;
		}
		
		/**
		 * get 可领取元宝
		 * @return 
		 */
		public function get canryuanbao(): int{
			return _canryuanbao;
		}
		
		/**
		 * set 可领取元宝
		 */
		public function set canryuanbao(value: int): void{
			this._canryuanbao = value;
		}
		
		/**
		 * get 临时元宝日志列表
		 * @return 
		 */
		public function get tpmyuanbaolonginfo(): Vector.<TmpYuanbaoLogInfo>{
			return _tpmyuanbaolonginfo;
		}
		
		/**
		 * set 临时元宝日志列表
		 */
		public function set tpmyuanbaolonginfo(value: Vector.<TmpYuanbaoLogInfo>): void{
			this._tpmyuanbaolonginfo = value;
		}
		
	}
}