package com.game.vip.handler{

	import com.game.vip.message.ResVIPAnnounceMessage;
	import net.Handler;

	public class ResVIPAnnounceHandler extends Handler {
	
		public override function action(): void{
			var msg: ResVIPAnnounceMessage = ResVIPAnnounceMessage(this.message);
			//TODO 添加消息处理
		}
	}
}