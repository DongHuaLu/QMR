package com.game.epalace.handler{

	import com.game.epalace.message.ResEpalaceDiceToClientMessage;
	import net.Handler;

	public class ResEpalaceDiceToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEpalaceDiceToClientMessage = ResEpalaceDiceToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}