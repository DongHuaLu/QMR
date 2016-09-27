package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsSearchMessage;
	import net.Handler;

	public class ResStallsSearchHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsSearchMessage = ResStallsSearchMessage(this.message);
			//TODO 添加消息处理
		}
	}
}