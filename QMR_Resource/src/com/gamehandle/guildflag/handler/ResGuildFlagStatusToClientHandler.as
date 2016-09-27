package com.game.guildflag.handler{

	import com.game.guildflag.message.ResGuildFlagStatusToClientMessage;
	import net.Handler;

	public class ResGuildFlagStatusToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildFlagStatusToClientMessage = ResGuildFlagStatusToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}