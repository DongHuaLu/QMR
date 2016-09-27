package com.game.horse.handler{

	import com.game.horse.message.ResOthersHorseInfoMessage;
	import net.Handler;

	public class ResOthersHorseInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOthersHorseInfoMessage = ResOthersHorseInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}