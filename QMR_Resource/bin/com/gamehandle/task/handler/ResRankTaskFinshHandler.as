package com.game.task.handler{

	import com.game.task.message.ResRankTaskFinshMessage;
	import net.Handler;

	public class ResRankTaskFinshHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRankTaskFinshMessage = ResRankTaskFinshMessage(this.message);
			//TODO 添加消息处理
		}
	}
}