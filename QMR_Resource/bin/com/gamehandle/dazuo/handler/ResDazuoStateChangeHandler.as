package com.game.dazuo.handler{

	import com.game.dazuo.message.ResDazuoStateChangeMessage;
	import net.Handler;

	public class ResDazuoStateChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDazuoStateChangeMessage = ResDazuoStateChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}