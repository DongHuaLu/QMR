package com.game.task.handler{

	import com.game.task.message.ResRewardsAbleActMessage;
	import net.Handler;

	public class ResRewardsAbleActHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRewardsAbleActMessage = ResRewardsAbleActMessage(this.message);
			//TODO 添加消息处理
		}
	}
}