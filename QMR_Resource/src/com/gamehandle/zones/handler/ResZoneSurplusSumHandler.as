package com.game.zones.handler{

	import com.game.zones.message.ResZoneSurplusSumMessage;
	import net.Handler;

	public class ResZoneSurplusSumHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneSurplusSumMessage = ResZoneSurplusSumMessage(this.message);
			//TODO 添加消息处理
		}
	}
}