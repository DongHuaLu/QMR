package com.game.cloak.handler{

	import com.game.cloak.message.ResStoneCombineMessage;
	import net.Handler;

	public class ResStoneCombineHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStoneCombineMessage = ResStoneCombineMessage(this.message);
			//TODO 添加消息处理
		}
	}
}