package com.game.task.handler{

	import com.game.task.message.ResRankTaskQuickFinshAllMessage;
	import net.Handler;

	public class ResRankTaskQuickFinshAllHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRankTaskQuickFinshAllMessage = ResRankTaskQuickFinshAllMessage(this.message);
			//TODO 添加消息处理
		}
	}
}