package com.game.fight.handler{

	import com.game.fight.message.ResAttackRangeMessage;
	import net.Handler;

	public class ResAttackRangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAttackRangeMessage = ResAttackRangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}