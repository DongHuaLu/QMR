package com.game.marriage.handler{

	import com.game.marriage.message.ResEdibleInfoToClientMessage;
	import net.Handler;

	public class ResEdibleInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEdibleInfoToClientMessage = ResEdibleInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}