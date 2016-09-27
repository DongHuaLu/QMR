package com.game.challenge.handler{

	import com.game.challenge.message.ResOpenChallengeToClientMessage;
	import net.Handler;

	public class ResOpenChallengeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOpenChallengeToClientMessage = ResOpenChallengeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}