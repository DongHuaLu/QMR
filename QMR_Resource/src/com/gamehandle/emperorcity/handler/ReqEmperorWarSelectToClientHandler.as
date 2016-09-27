package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ReqEmperorWarSelectToClientMessage;
	import net.Handler;

	public class ReqEmperorWarSelectToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ReqEmperorWarSelectToClientMessage = ReqEmperorWarSelectToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}