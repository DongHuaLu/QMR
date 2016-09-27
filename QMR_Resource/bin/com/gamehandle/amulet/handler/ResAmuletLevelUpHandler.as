package com.game.amulet.handler{

	import com.game.amulet.message.ResAmuletLevelUpMessage;
	import net.Handler;

	public class ResAmuletLevelUpHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAmuletLevelUpMessage = ResAmuletLevelUpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}