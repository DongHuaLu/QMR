package com.game.player.handler{

	import com.game.player.message.ResGetPlayerAppearanceInfMessage;
	import net.Handler;

	public class ResGetPlayerAppearanceInfHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetPlayerAppearanceInfMessage = ResGetPlayerAppearanceInfMessage(this.message);
			//TODO 添加消息处理
		}
	}
}