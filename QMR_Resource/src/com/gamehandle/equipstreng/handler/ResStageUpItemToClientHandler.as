package com.game.equipstreng.handler{

	import com.game.equipstreng.message.ResStageUpItemToClientMessage;
	import net.Handler;

	public class ResStageUpItemToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStageUpItemToClientMessage = ResStageUpItemToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}