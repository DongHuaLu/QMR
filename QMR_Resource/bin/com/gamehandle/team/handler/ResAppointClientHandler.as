package com.game.team.handler{

	import com.game.team.message.ResAppointClientMessage;
	import net.Handler;

	public class ResAppointClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAppointClientMessage = ResAppointClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}