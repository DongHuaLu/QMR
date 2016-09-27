package com.game.backpack.handler{

	import com.game.backpack.message.ResGetItemReasonsMessage;
	import net.Handler;

	public class ResGetItemReasonsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetItemReasonsMessage = ResGetItemReasonsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}