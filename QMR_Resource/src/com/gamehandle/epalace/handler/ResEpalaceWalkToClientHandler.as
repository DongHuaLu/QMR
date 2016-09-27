package com.game.epalace.handler{

	import com.game.epalace.message.ResEpalaceWalkToClientMessage;
	import net.Handler;

	public class ResEpalaceWalkToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEpalaceWalkToClientMessage = ResEpalaceWalkToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}