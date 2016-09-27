package com.game.gem.handler{

	import com.game.gem.message.ResOpenGemPanelMessage;
	import net.Handler;

	public class ResOpenGemPanelHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOpenGemPanelMessage = ResOpenGemPanelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}