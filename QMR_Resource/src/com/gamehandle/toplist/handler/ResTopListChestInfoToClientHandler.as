package com.game.toplist.handler{

	import com.game.toplist.message.ResTopListChestInfoToClientMessage;
	import net.Handler;

	public class ResTopListChestInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTopListChestInfoToClientMessage = ResTopListChestInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}