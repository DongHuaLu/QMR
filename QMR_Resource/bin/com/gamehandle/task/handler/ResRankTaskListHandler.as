package com.game.task.handler{

	import com.game.task.message.ResRankTaskListMessage;
	import net.Handler;

	public class ResRankTaskListHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRankTaskListMessage = ResRankTaskListMessage(this.message);
			//TODO 添加消息处理
		}
	}
}