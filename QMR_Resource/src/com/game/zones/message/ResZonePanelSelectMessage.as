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
	 * 返回当前副本信息
	 */
	public class ResZonePanelSelectMessage extends Message {
	
		//返回当前副本信息
		private var _raidzoneinfolist: RaidZoneInfo;
		
		//手动可领取状态，1有奖励，0没有
		private var _manualstatus: int;
		
		//自动扫荡可领取状态，1有奖励，0没有
		private var _autostatus: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//返回当前副本信息
			writeBean(_raidzoneinfolist);
			//手动可领取状态，1有奖励，0没有
			writeInt(_manualstatus);
			//自动扫荡可领取状态，1有奖励，0没有
			writeInt(_autostatus);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//返回当前副本信息
			_raidzoneinfolist = readBean(RaidZoneInfo) as RaidZoneInfo;
			//手动可领取状态，1有奖励，0没有
			_manualstatus = readInt();
			//自动扫荡可领取状态，1有奖励，0没有
			_autostatus = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128101;
		}
		
		/**
		 * get 返回当前副本信息
		 * @return 
		 */
		public function get raidzoneinfolist(): RaidZoneInfo{
			return _raidzoneinfolist;
		}
		
		/**
		 * set 返回当前副本信息
		 */
		public function set raidzoneinfolist(value: RaidZoneInfo): void{
			this._raidzoneinfolist = value;
		}
		
		/**
		 * get 手动可领取状态，1有奖励，0没有
		 * @return 
		 */
		public function get manualstatus(): int{
			return _manualstatus;
		}
		
		/**
		 * set 手动可领取状态，1有奖励，0没有
		 */
		public function set manualstatus(value: int): void{
			this._manualstatus = value;
		}
		
		/**
		 * get 自动扫荡可领取状态，1有奖励，0没有
		 * @return 
		 */
		public function get autostatus(): int{
			return _autostatus;
		}
		
		/**
		 * set 自动扫荡可领取状态，1有奖励，0没有
		 */
		public function set autostatus(value: int): void{
			this._autostatus = value;
		}
		
	}
}