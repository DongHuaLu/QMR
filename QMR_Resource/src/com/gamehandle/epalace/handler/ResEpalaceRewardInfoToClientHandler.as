package com.game.epalace.handler{

	import com.game.epalace.message.ResEpalaceRewardInfoToClientMessage;
	import net.Handler;

	public class ResEpalaceRewardInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEpalaceRewardInfoToClientMessage = ResEpalaceRewardInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}