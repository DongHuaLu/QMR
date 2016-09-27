package com.game.activities.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 前端请求领取信息
	 */
	public class ReqGetRewardMessage extends Message {
	
		//活动id
		private var _activityId: int;
		
		//活动类型
		private var _activityType: int;
		
		//选择奖励
		private var _selected: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//活动id
			writeInt(_activityId);
			//活动类型
			writeInt(_activityType);
			//选择奖励
			writeInt(_selected);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//活动id
			_activityId = readInt();
			//活动类型
			_activityType = readInt();
			//选择奖励
			_selected = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 138201;
		}
		
		/**
		 * get 活动id
		 * @return 
		 */
		public function get activityId(): int{
			return _activityId;
		}
		
		/**
		 * set 活动id
		 */
		public function set activityId(value: int): void{
			this._activityId = value;
		}
		
		/**
		 * get 活动类型
		 * @return 
		 */
		public function get activityType(): int{
			return _activityType;
		}
		
		/**
		 * set 活动类型
		 */
		public function set activityType(value: int): void{
			this._activityType = value;
		}
		
		/**
		 * get 选择奖励
		 * @return 
		 */
		public function get selected(): int{
			return _selected;
		}
		
		/**
		 * set 选择奖励
		 */
		public function set selected(value: int): void{
			this._selected = value;
		}
		
	}
}