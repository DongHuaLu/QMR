package com.game.player.handler{

	import com.game.player.message.ResChangePKStateMessage;
	import net.Handler;

	public class ResChangePKStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangePKStateMessage = ResChangePKStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}