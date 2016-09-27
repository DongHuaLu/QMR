package com.game.guildflag.handler{

	import com.game.guildflag.message.ResOpenGuildFlagToClientMessage;
	import net.Handler;

	public class ResOpenGuildFlagToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOpenGuildFlagToClientMessage = ResOpenGuildFlagToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}