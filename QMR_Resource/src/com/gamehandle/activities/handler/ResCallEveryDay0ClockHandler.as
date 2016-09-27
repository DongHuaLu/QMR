package com.game.activities.handler{

	import com.game.activities.message.ResCallEveryDay0ClockMessage;
	import net.Handler;

	public class ResCallEveryDay0ClockHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCallEveryDay0ClockMessage = ResCallEveryDay0ClockMessage(this.message);
			//TODO 添加消息处理
		}
	}
}