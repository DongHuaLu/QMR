package com.game.player.handler{

	import com.game.player.message.ResPlayerLevelUpMessage;
	import net.Handler;

	public class ResPlayerLevelUpHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerLevelUpMessage = ResPlayerLevelUpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}