package com.game.scripts.handler{

	import com.game.scripts.message.ResScriptPaneleMessage;
	import net.Handler;

	public class ResScriptPaneleHandler extends Handler {
	
		public override function action(): void{
			var msg: ResScriptPaneleMessage = ResScriptPaneleMessage(this.message);
			//TODO 添加消息处理
		}
	}
}