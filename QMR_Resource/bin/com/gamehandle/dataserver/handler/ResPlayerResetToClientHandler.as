package com.game.dataserver.handler{

	import com.game.dataserver.message.ResPlayerResetToClientMessage;
	import net.Handler;

	public class ResPlayerResetToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerResetToClientMessage = ResPlayerResetToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}