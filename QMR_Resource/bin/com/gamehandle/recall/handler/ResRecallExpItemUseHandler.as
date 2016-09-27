package com.game.recall.handler{

	import com.game.recall.message.ResRecallExpItemUseMessage;
	import net.Handler;

	public class ResRecallExpItemUseHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRecallExpItemUseMessage = ResRecallExpItemUseMessage(this.message);
			//TODO 添加消息处理
		}
	}
}