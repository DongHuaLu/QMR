package com.game.task.handler{

	import com.game.task.message.ResConquerTaskChangeMessage;
	import net.Handler;

	public class ResConquerTaskChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResConquerTaskChangeMessage = ResConquerTaskChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}