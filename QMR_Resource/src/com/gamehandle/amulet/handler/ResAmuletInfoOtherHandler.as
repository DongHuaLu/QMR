package com.game.amulet.handler{

	import com.game.amulet.message.ResAmuletInfoOtherMessage;
	import net.Handler;

	public class ResAmuletInfoOtherHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAmuletInfoOtherMessage = ResAmuletInfoOtherMessage(this.message);
			//TODO 添加消息处理
		}
	}
}