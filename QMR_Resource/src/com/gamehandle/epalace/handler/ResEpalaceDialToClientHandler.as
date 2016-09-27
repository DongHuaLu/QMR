package com.game.epalace.handler{

	import com.game.epalace.message.ResEpalaceDialToClientMessage;
	import net.Handler;

	public class ResEpalaceDialToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEpalaceDialToClientMessage = ResEpalaceDialToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}