package com.game.marriage.handler{

	import com.game.marriage.message.ResLaunchProposeStartToClientMessage;
	import net.Handler;

	public class ResLaunchProposeStartToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLaunchProposeStartToClientMessage = ResLaunchProposeStartToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}