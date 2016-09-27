package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsExpMessage;
	import net.Handler;

	public class ResStallsExpHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsExpMessage = ResStallsExpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}