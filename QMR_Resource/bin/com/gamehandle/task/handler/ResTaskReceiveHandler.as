package com.game.task.handler{

	import com.game.task.message.ResTaskReceiveMessage;
	import net.Handler;

	public class ResTaskReceiveHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTaskReceiveMessage = ResTaskReceiveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}