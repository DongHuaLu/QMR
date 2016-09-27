package com.game.task.handler{

	import com.game.task.message.ResGiveUpTaskMessage;
	import net.Handler;

	public class ResGiveUpTaskHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGiveUpTaskMessage = ResGiveUpTaskMessage(this.message);
			//TODO 添加消息处理
		}
	}
}