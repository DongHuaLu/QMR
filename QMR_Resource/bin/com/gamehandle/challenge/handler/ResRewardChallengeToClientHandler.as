package com.game.challenge.handler{

	import com.game.challenge.message.ResRewardChallengeToClientMessage;
	import net.Handler;

	public class ResRewardChallengeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRewardChallengeToClientMessage = ResRewardChallengeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}