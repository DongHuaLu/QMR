package com.game.activities.handler{

	import com.game.activities.message.ResActivitiesInfoMessage;
	import net.Handler;

	public class ResActivitiesInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResActivitiesInfoMessage = ResActivitiesInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}