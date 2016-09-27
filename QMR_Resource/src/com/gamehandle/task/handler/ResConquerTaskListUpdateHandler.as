package com.game.task.handler{

	import com.game.task.message.ResConquerTaskListUpdateMessage;
	import net.Handler;

	public class ResConquerTaskListUpdateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResConquerTaskListUpdateMessage = ResConquerTaskListUpdateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}