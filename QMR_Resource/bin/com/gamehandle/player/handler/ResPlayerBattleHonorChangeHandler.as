package com.game.player.handler{

	import com.game.player.message.ResPlayerBattleHonorChangeMessage;
	import net.Handler;

	public class ResPlayerBattleHonorChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerBattleHonorChangeMessage = ResPlayerBattleHonorChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}