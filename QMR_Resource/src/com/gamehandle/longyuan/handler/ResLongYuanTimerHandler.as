package com.game.longyuan.handler{

	import com.game.longyuan.message.ResLongYuanTimerMessage;
	import net.Handler;

	public class ResLongYuanTimerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLongYuanTimerMessage = ResLongYuanTimerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}