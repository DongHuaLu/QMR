package com.game.signwage.handler{

	import com.game.signwage.message.RessignnumToClientMessage;
	import net.Handler;

	public class RessignnumToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: RessignnumToClientMessage = RessignnumToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}