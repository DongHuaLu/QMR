package com.game.dataserver.handler{

	import com.game.dataserver.message.ResPlayerEnterToClientMessage;
	import net.Handler;

	public class ResPlayerEnterToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerEnterToClientMessage = ResPlayerEnterToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}