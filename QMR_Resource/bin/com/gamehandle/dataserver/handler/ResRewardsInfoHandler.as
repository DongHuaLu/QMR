package com.game.dataserver.handler{

	import com.game.dataserver.message.ResRewardsInfoMessage;
	import net.Handler;

	public class ResRewardsInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRewardsInfoMessage = ResRewardsInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}