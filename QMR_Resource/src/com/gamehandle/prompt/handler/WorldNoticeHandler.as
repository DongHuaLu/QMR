package com.game.prompt.handler{

	import com.game.prompt.message.WorldNoticeMessage;
	import net.Handler;

	public class WorldNoticeHandler extends Handler {
	
		public override function action(): void{
			var msg: WorldNoticeMessage = WorldNoticeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}