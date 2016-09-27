package com.game.activities.handler{

	import com.game.activities.message.ResActivitiesInfoWorldMessage;
	import net.Handler;

	public class ResActivitiesInfoWorldHandler extends Handler {
	
		public override function action(): void{
			var msg: ResActivitiesInfoWorldMessage = ResActivitiesInfoWorldMessage(this.message);
			//TODO 添加消息处理
		}
	}
}