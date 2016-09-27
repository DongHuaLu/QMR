package com.game.pet.handler{

	import com.game.pet.message.ResPetTargetMessage;
	import net.Handler;

	public class ResPetTargetHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetTargetMessage = ResPetTargetMessage(this.message);
			//TODO 添加消息处理
		}
	}
}