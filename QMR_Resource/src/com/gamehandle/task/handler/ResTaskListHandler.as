package com.game.task.handler{

	import com.game.task.message.ResTaskListMessage;
	import net.Handler;

	public class ResTaskListHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTaskListMessage = ResTaskListMessage(this.message);
			//TODO 添加消息处理
		}
	}
}