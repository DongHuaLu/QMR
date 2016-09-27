package com.game.dataserver.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获得奖励信息
	 */
	public class ResRewardsInfoMessage extends Message {
	
		//奖励数据
		private var _reward: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//奖励数据
			writeShort(_reward.length);
			for (i = 0; i < _reward.length; i++) {
				writeBean(_reward[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//奖励数据
			var reward_length : int = readShort();
			for (i = 0; i < reward_length; i++) {
				_reward[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203104;
		}
		
		/**
		 * get 奖励数据
		 * @return 
		 */
		public function get reward(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _reward;
		}
		
		/**
		 * set 奖励数据
		 */
		public function set reward(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._reward = value;
		}
		
	}
}