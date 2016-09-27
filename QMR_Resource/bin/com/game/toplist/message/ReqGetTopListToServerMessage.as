package com.game.toplist.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取排行榜信息
	 */
	public class ReqGetTopListToServerMessage extends Message {
	
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力9竞技场连胜排名
		private var _toptype: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力9竞技场连胜排名
			writeByte(_toptype);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力9竞技场连胜排名
			_toptype = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142201;
		}
		
		/**
		 * get 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力9竞技场连胜排名
		 * @return 
		 */
		public function get toptype(): int{
			return _toptype;
		}
		
		/**
		 * set 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力9竞技场连胜排名
		 */
		public function set toptype(value: int): void{
			this._toptype = value;
		}
		
	}
}