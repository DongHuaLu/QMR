package com.game.zones.message{
	import com.game.zones.bean.ZoneRewardNumInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知前端-可领取奖励
	 */
	public class ResZoneNoticeRewardMessage extends Message {
	
		//通知可领取奖励数量
		private var _zoneRewardnuminfo: Vector.<ZoneRewardNumInfo> = new Vector.<ZoneRewardNumInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//通知可领取奖励数量
			writeShort(_zoneRewardnuminfo.length);
			for (i = 0; i < _zoneRewardnuminfo.length; i++) {
				writeBean(_zoneRewardnuminfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//通知可领取奖励数量
			var zoneRewardnuminfo_length : int = readShort();
			for (i = 0; i < zoneRewardnuminfo_length; i++) {
				_zoneRewardnuminfo[i] = readBean(ZoneRewardNumInfo) as ZoneRewardNumInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128108;
		}
		
		/**
		 * get 通知可领取奖励数量
		 * @return 
		 */
		public function get zoneRewardnuminfo(): Vector.<ZoneRewardNumInfo>{
			return _zoneRewardnuminfo;
		}
		
		/**
		 * set 通知可领取奖励数量
		 */
		public function set zoneRewardnuminfo(value: Vector.<ZoneRewardNumInfo>): void{
			this._zoneRewardnuminfo = value;
		}
		
	}
}