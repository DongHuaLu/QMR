package com.game.version.handler{

	import com.game.version.message.ResVersionUpdateToClientMessage;
	import net.Handler;

	public class ResVersionUpdateToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResVersionUpdateToClientMessage = ResVersionUpdateToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}