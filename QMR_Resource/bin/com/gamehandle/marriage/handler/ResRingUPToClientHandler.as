package com.game.marriage.handler{

	import com.game.marriage.message.ResRingUPToClientMessage;
	import net.Handler;

	public class ResRingUPToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRingUPToClientMessage = ResRingUPToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}