package com.game.marriage.handler{

	import com.game.marriage.message.ResReplaceRingToClientMessage;
	import net.Handler;

	public class ResReplaceRingToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResReplaceRingToClientMessage = ResReplaceRingToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}