package com.game.dazuo.handler{

	import com.game.dazuo.message.ResUsedsuccessfullyMessage;
	import net.Handler;

	public class ResUsedsuccessfullyHandler extends Handler {
	
		public override function action(): void{
			var msg: ResUsedsuccessfullyMessage = ResUsedsuccessfullyMessage(this.message);
			//TODO 添加消息处理
		}
	}
}