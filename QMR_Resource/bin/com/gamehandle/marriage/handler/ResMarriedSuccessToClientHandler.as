package com.game.marriage.handler{

	import com.game.marriage.message.ResMarriedSuccessToClientMessage;
	import net.Handler;

	public class ResMarriedSuccessToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMarriedSuccessToClientMessage = ResMarriedSuccessToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}