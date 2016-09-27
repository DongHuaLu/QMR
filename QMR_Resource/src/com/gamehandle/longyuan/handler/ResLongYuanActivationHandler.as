package com.game.longyuan.handler{

	import com.game.longyuan.message.ResLongYuanActivationMessage;
	import net.Handler;

	public class ResLongYuanActivationHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLongYuanActivationMessage = ResLongYuanActivationMessage(this.message);
			//TODO 添加消息处理
		}
	}
}