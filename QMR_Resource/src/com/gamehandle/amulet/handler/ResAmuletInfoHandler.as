package com.game.amulet.handler{

	import com.game.amulet.message.ResAmuletInfoMessage;
	import net.Handler;

	public class ResAmuletInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAmuletInfoMessage = ResAmuletInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}