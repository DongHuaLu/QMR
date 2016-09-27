package com.game.fight.handler{

	import com.game.fight.message.ResAttackResultMessage;
	import net.Handler;

	public class ResAttackResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAttackResultMessage = ResAttackResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}