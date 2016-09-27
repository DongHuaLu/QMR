package com.game.horse.handler{

	import com.game.horse.message.ReshorseStageUpResultMessage;
	import net.Handler;

	public class ReshorseStageUpResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseStageUpResultMessage = ReshorseStageUpResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}