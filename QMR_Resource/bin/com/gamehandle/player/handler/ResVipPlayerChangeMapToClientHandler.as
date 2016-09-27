package com.game.player.handler{

	import com.game.player.message.ResVipPlayerChangeMapToClientMessage;
	import net.Handler;

	public class ResVipPlayerChangeMapToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResVipPlayerChangeMapToClientMessage = ResVipPlayerChangeMapToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}