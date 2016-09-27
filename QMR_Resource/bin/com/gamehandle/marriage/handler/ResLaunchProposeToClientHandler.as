package com.game.marriage.handler{

	import com.game.marriage.message.ResLaunchProposeToClientMessage;
	import net.Handler;

	public class ResLaunchProposeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLaunchProposeToClientMessage = ResLaunchProposeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}