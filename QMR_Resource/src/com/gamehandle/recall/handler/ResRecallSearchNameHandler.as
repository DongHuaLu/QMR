package com.game.recall.handler{

	import com.game.recall.message.ResRecallSearchNameMessage;
	import net.Handler;

	public class ResRecallSearchNameHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRecallSearchNameMessage = ResRecallSearchNameMessage(this.message);
			//TODO 添加消息处理
		}
	}
}