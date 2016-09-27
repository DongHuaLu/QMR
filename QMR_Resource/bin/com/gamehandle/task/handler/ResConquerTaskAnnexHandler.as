package com.game.task.handler{

	import com.game.task.message.ResConquerTaskAnnexMessage;
	import net.Handler;

	public class ResConquerTaskAnnexHandler extends Handler {
	
		public override function action(): void{
			var msg: ResConquerTaskAnnexMessage = ResConquerTaskAnnexMessage(this.message);
			//TODO 添加消息处理
		}
	}
}