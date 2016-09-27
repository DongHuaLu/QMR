package com.game.zones.message{
	import com.game.zones.bean.ZoneTeamInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 展示副本活动信息
	 */
	public class ResZoneTeamShowToClientMessage extends Message {
	
		//多人副本信息
		private var _zoneteaminfo: Vector.<ZoneTeamInfo> = new Vector.<ZoneTeamInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//多人副本信息
			writeShort(_zoneteaminfo.length);
			for (i = 0; i < _zoneteaminfo.length; i++) {
				writeBean(_zoneteaminfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//多人副本信息
			var zoneteaminfo_length : int = readShort();
			for (i = 0; i < zoneteaminfo_length; i++) {
				_zoneteaminfo[i] = readBean(ZoneTeamInfo) as ZoneTeamInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128116;
		}
		
		/**
		 * get 多人副本信息
		 * @return 
		 */
		public function get zoneteaminfo(): Vector.<ZoneTeamInfo>{
			return _zoneteaminfo;
		}
		
		/**
		 * set 多人副本信息
		 */
		public function set zoneteaminfo(value: Vector.<ZoneTeamInfo>): void{
			this._zoneteaminfo = value;
		}
		
	}
}