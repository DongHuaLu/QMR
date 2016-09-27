package com.game.task.handler{

	import com.game.task.message.ResTaskFinshMessage;
	import net.Handler;

	public class ResTaskFinshHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTaskFinshMessage = ResTaskFinshMessage(this.message);
			//TODO 添加消息处理
		}
	}
}