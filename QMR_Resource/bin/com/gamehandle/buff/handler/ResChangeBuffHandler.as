package com.game.buff.handler{

	import com.game.buff.message.ResChangeBuffMessage;
	import net.Handler;

	public class ResChangeBuffHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangeBuffMessage = ResChangeBuffMessage(this.message);
			//TODO 添加消息处理
		}
	}
}