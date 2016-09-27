package com.game.team.handler{

	import com.game.team.message.ResMapSearchMemberNameClientMessage;
	import net.Handler;

	public class ResMapSearchMemberNameClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMapSearchMemberNameClientMessage = ResMapSearchMemberNameClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}