package com.game.horse.handler{

	import com.game.horse.message.ResHorseInfoMessage;
	import net.Handler;

	public class ResHorseInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHorseInfoMessage = ResHorseInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}