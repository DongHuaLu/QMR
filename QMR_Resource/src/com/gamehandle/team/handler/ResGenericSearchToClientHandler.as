package com.game.team.handler{

	import com.game.team.message.ResGenericSearchToClientMessage;
	import net.Handler;

	public class ResGenericSearchToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGenericSearchToClientMessage = ResGenericSearchToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}