package com.game.zones.handler{

	import com.game.zones.message.ResZonePanelSelectMessage;
	import net.Handler;

	public class ResZonePanelSelectHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZonePanelSelectMessage = ResZonePanelSelectMessage(this.message);
			//TODO 添加消息处理
		}
	}
}