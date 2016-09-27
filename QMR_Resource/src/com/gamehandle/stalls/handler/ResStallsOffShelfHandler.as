package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsOffShelfMessage;
	import net.Handler;

	public class ResStallsOffShelfHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsOffShelfMessage = ResStallsOffShelfMessage(this.message);
			//TODO 添加消息处理
		}
	}
}