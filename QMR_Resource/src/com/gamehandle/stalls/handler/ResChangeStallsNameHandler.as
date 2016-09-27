package com.game.stalls.handler{

	import com.game.stalls.message.ResChangeStallsNameMessage;
	import net.Handler;

	public class ResChangeStallsNameHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangeStallsNameMessage = ResChangeStallsNameMessage(this.message);
			//TODO 添加消息处理
		}
	}
}