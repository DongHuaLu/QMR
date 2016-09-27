package com.game.zones.message{
	import com.game.zones.bean.ZoneApplyDataInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送多人副本报名名单（单个人报名）
	 */
	public class ResZoneApplyDataInfoToClientMessage extends Message {
	
		//多人副本报名名单
		private var _zoneapplydatainfo: ZoneApplyDataInfo;
		
		//类型：0第一次报名，1其他人加入，2人数足够
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//多人副本报名名单
			writeBean(_zoneapplydatainfo);
			//类型：0第一次报名，1其他人加入，2人数足够
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//多人副本报名名单
			_zoneapplydatainfo = readBean(ZoneApplyDataInfo) as ZoneApplyDataInfo;
			//类型：0第一次报名，1其他人加入，2人数足够
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128120;
		}
		
		/**
		 * get 多人副本报名名单
		 * @return 
		 */
		public function get zoneapplydatainfo(): ZoneApplyDataInfo{
			return _zoneapplydatainfo;
		}
		
		/**
		 * set 多人副本报名名单
		 */
		public function set zoneapplydatainfo(value: ZoneApplyDataInfo): void{
			this._zoneapplydatainfo = value;
		}
		
		/**
		 * get 类型：0第一次报名，1其他人加入，2人数足够
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：0第一次报名，1其他人加入，2人数足够
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}