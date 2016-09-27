package com.game.player.handler{

	import com.game.player.message.ResPlayerBattleExpChangeMessage;
	import net.Handler;

	public class ResPlayerBattleExpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerBattleExpChangeMessage = ResPlayerBattleExpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}