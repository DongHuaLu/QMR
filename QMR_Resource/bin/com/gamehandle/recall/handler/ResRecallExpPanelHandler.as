package com.game.recall.handler{

	import com.game.recall.message.ResRecallExpPanelMessage;
	import net.Handler;

	public class ResRecallExpPanelHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRecallExpPanelMessage = ResRecallExpPanelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}