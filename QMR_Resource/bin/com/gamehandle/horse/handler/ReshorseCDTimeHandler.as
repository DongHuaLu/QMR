package com.game.horse.handler{

	import com.game.horse.message.ReshorseCDTimeMessage;
	import net.Handler;

	public class ReshorseCDTimeHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseCDTimeMessage = ReshorseCDTimeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}