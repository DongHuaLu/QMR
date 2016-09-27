package com.game.toplist.handler{

	import com.game.toplist.message.ResToplistArenaGiftStateMessage;
	import net.Handler;

	public class ResToplistArenaGiftStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResToplistArenaGiftStateMessage = ResToplistArenaGiftStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}