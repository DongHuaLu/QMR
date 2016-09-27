package com.game.toplist.handler{

	import com.game.toplist.message.ResToplistArenaStateMessage;
	import net.Handler;

	public class ResToplistArenaStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResToplistArenaStateMessage = ResToplistArenaStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}