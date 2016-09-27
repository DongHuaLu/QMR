package com.game.task.handler{

	import com.game.task.message.ResDailyTaskChangeMessage;
	import net.Handler;

	public class ResDailyTaskChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDailyTaskChangeMessage = ResDailyTaskChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}