package com.game.pet.handler{

	import com.game.pet.message.ResPetAttributeChangeMessage;
	import net.Handler;

	public class ResPetAttributeChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetAttributeChangeMessage = ResPetAttributeChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}