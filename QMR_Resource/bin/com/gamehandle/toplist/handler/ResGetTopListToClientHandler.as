package com.game.toplist.handler{

	import com.game.toplist.message.ResGetTopListToClientMessage;
	import net.Handler;

	public class ResGetTopListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetTopListToClientMessage = ResGetTopListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}