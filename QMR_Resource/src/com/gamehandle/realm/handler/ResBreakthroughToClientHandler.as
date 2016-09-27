package com.game.realm.handler{

	import com.game.realm.message.ResBreakthroughToClientMessage;
	import net.Handler;

	public class ResBreakthroughToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBreakthroughToClientMessage = ResBreakthroughToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}