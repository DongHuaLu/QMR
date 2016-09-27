package com.game.monster.handler{

	import com.game.monster.message.ResQueryBossListResultMessage;
	import net.Handler;

	public class ResQueryBossListResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResQueryBossListResultMessage = ResQueryBossListResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}