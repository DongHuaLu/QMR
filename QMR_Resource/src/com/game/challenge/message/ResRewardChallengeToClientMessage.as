package com.game.challenge.message{
	import com.game.challenge.bean.ChallengeRewardInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送挑战面板奖励消息
	 */
	public class ResRewardChallengeToClientMessage extends Message {
	
		//挑战奖励信息
		private var _rewardInfo: Vector.<ChallengeRewardInfo> = new Vector.<ChallengeRewardInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//挑战奖励信息
			writeShort(_rewardInfo.length);
			for (i = 0; i < _rewardInfo.length; i++) {
				writeBean(_rewardInfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//挑战奖励信息
			var rewardInfo_length : int = readShort();
			for (i = 0; i < rewardInfo_length; i++) {
				_rewardInfo[i] = readBean(ChallengeRewardInfo) as ChallengeRewardInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 144102;
		}
		
		/**
		 * get 挑战奖励信息
		 * @return 
		 */
		public function get rewardInfo(): Vector.<ChallengeRewardInfo>{
			return _rewardInfo;
		}
		
		/**
		 * set 挑战奖励信息
		 */
		public function set rewardInfo(value: Vector.<ChallengeRewardInfo>): void{
			this._rewardInfo = value;
		}
		
	}
}