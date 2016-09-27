package com.game.horse.handler{

	import com.game.horse.message.ReshorseStageUpPanelMessage;
	import net.Handler;

	public class ReshorseStageUpPanelHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseStageUpPanelMessage = ReshorseStageUpPanelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}