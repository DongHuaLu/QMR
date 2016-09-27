package com.game.zones.message{
	import com.game.zones.bean.RaidZoneInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送所有扫荡副本信息
	 */
	public class ResAllRaidZoneInfoMessage extends Message {
	
		//所有扫荡副本信息
		private var _raidzoneinfolist: Vector.<RaidZoneInfo> = new Vector.<RaidZoneInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//所有扫荡副本信息
			writeShort(_raidzoneinfolist.length);
			for (i = 0; i < _raidzoneinfolist.length; i++) {
				writeBean(_raidzoneinfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//所有扫荡副本信息
			var raidzoneinfolist_length : int = readShort();
			for (i = 0; i < raidzoneinfolist_length; i++) {
				_raidzoneinfolist[i] = readBean(RaidZoneInfo) as RaidZoneInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128106;
		}
		
		/**
		 * get 所有扫荡副本信息
		 * @return 
		 */
		public function get raidzoneinfolist(): Vector.<RaidZoneInfo>{
			return _raidzoneinfolist;
		}
		
		/**
		 * set 所有扫荡副本信息
		 */
		public function set raidzoneinfolist(value: Vector.<RaidZoneInfo>): void{
			this._raidzoneinfolist = value;
		}
		
	}
}