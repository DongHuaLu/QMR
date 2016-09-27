package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsLooklogMessage;
	import net.Handler;

	public class ResStallsLooklogHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsLooklogMessage = ResStallsLooklogMessage(this.message);
			//TODO 添加消息处理
		}
	}
}