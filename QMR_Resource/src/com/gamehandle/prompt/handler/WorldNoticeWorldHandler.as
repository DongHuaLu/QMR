package com.game.prompt.handler{

	import com.game.prompt.message.WorldNoticeWorldMessage;
	import net.Handler;

	public class WorldNoticeWorldHandler extends Handler {
	
		public override function action(): void{
			var msg: WorldNoticeWorldMessage = WorldNoticeWorldMessage(this.message);
			//TODO 添加消息处理
		}
	}
}