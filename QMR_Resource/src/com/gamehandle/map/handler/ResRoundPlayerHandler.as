package com.game.map.handler{

	import com.game.map.message.ResRoundPlayerMessage;
	import net.Handler;

	public class ResRoundPlayerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundPlayerMessage = ResRoundPlayerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}