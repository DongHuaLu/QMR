package com.game.task.handler{

	import com.game.task.message.ResRankTaskChangeMessage;
	import net.Handler;

	public class ResRankTaskChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRankTaskChangeMessage = ResRankTaskChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}