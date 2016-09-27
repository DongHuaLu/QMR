package com.game.pet.handler{

	import com.game.pet.message.ResPetShowMessage;
	import net.Handler;

	public class ResPetShowHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetShowMessage = ResPetShowMessage(this.message);
			//TODO 添加消息处理
		}
	}
}