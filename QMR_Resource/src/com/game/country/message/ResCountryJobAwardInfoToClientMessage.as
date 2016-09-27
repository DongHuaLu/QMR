package com.game.country.message{
	import com.game.country.bean.JobAwardInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 王城职位领奖消息
	 */
	public class ResCountryJobAwardInfoToClientMessage extends Message {
	
		//职位奖励领取情况
		private var _damageinfo: Vector.<JobAwardInfo> = new Vector.<JobAwardInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//职位奖励领取情况
			writeShort(_damageinfo.length);
			for (i = 0; i < _damageinfo.length; i++) {
				writeBean(_damageinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//职位奖励领取情况
			var damageinfo_length : int = readShort();
			for (i = 0; i < damageinfo_length; i++) {
				_damageinfo[i] = readBean(JobAwardInfo) as JobAwardInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146113;
		}
		
		/**
		 * get 职位奖励领取情况
		 * @return 
		 */
		public function get damageinfo(): Vector.<JobAwardInfo>{
			return _damageinfo;
		}
		
		/**
		 * set 职位奖励领取情况
		 */
		public function set damageinfo(value: Vector.<JobAwardInfo>): void{
			this._damageinfo = value;
		}
		
	}
}