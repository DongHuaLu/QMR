package com.game.gem.handler{

	import com.game.gem.message.ResGemStrengthenPanelMessage;
	import net.Handler;

	public class ResGemStrengthenPanelHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGemStrengthenPanelMessage = ResGemStrengthenPanelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}