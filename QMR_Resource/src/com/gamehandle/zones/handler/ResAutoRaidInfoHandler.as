package com.game.zones.handler{

	import com.game.zones.message.ResAutoRaidInfoMessage;
	import net.Handler;

	public class ResAutoRaidInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAutoRaidInfoMessage = ResAutoRaidInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}