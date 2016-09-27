package com.game.contral.handler{

	import com.game.contral.message.ResContralCloseListMessage;
	import net.Handler;

	public class ResContralCloseListHandler extends Handler {
	
		public override function action(): void{
			var msg: ResContralCloseListMessage = ResContralCloseListMessage(this.message);
			//TODO 添加消息处理
		}
	}
}