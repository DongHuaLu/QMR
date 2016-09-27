package com.game.toplist.handler{

	import com.game.toplist.message.ResGetTopTitleToClientMessage;
	import net.Handler;

	public class ResGetTopTitleToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetTopTitleToClientMessage = ResGetTopTitleToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}