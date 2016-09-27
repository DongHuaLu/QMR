package com.game.toplist.handler{

	import com.game.toplist.message.ResToplistArenaMessage;
	import net.Handler;

	public class ResToplistArenaHandler extends Handler {
	
		public override function action(): void{
			var msg: ResToplistArenaMessage = ResToplistArenaMessage(this.message);
			//TODO 添加消息处理
		}
	}
}