package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResEmperorWarTimeRewardToClientMessage;
	import net.Handler;

	public class ResEmperorWarTimeRewardToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEmperorWarTimeRewardToClientMessage = ResEmperorWarTimeRewardToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}