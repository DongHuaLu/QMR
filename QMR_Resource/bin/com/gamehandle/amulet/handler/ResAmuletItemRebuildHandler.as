package com.game.amulet.handler{

	import com.game.amulet.message.ResAmuletItemRebuildMessage;
	import net.Handler;

	public class ResAmuletItemRebuildHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAmuletItemRebuildMessage = ResAmuletItemRebuildMessage(this.message);
			//TODO 添加消息处理
		}
	}
}