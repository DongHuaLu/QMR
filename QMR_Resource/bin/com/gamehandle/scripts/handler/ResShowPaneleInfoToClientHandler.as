package com.game.scripts.handler{

	import com.game.scripts.message.ResShowPaneleInfoToClientMessage;
	import net.Handler;

	public class ResShowPaneleInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShowPaneleInfoToClientMessage = ResShowPaneleInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}