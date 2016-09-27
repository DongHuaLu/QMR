package com.game.recall.handler{

	import com.game.recall.message.ResRecallHistoryMessage;
	import net.Handler;

	public class ResRecallHistoryHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRecallHistoryMessage = ResRecallHistoryMessage(this.message);
			//TODO 添加消息处理
		}
	}
}