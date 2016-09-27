package com.game.longyuan.handler{

	import com.game.longyuan.message.ResShowEffectToClientMessage;
	import net.Handler;

	public class ResShowEffectToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShowEffectToClientMessage = ResShowEffectToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}