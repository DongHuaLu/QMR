package com.game.task.handler{

	import com.game.task.message.ResMainTaskChangeMessage;
	import net.Handler;

	public class ResMainTaskChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMainTaskChangeMessage = ResMainTaskChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}