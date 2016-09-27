package com.game.pet.handler{

	import com.game.pet.message.ResPetHTCDClearResultMessage;
	import net.Handler;

	public class ResPetHTCDClearResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetHTCDClearResultMessage = ResPetHTCDClearResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}