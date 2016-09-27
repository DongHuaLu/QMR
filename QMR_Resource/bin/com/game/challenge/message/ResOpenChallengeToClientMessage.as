package com.game.challenge.message{
	import com.game.challenge.bean.ChallengeInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送挑战面板消息
	 */
	public class ResOpenChallengeToClientMessage extends Message {
	
		//挑战信息
		private var _challengeInfo: ChallengeInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//挑战信息
			writeBean(_challengeInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//挑战信息
			_challengeInfo = readBean(ChallengeInfo) as ChallengeInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 144101;
		}
		
		/**
		 * get 挑战信息
		 * @return 
		 */
		public function get challengeInfo(): ChallengeInfo{
			return _challengeInfo;
		}
		
		/**
		 * set 挑战信息
		 */
		public function set challengeInfo(value: ChallengeInfo): void{
			this._challengeInfo = value;
		}
		
	}
}