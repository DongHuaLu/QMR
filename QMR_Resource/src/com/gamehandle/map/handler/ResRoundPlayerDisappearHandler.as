package com.game.map.handler{

	import com.game.map.message.ResRoundPlayerDisappearMessage;
	import net.Handler;

	public class ResRoundPlayerDisappearHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundPlayerDisappearMessage = ResRoundPlayerDisappearMessage(this.message);
			//TODO 添加消息处理
		}
	}
}