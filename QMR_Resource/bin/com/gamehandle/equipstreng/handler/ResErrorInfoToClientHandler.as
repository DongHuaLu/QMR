package com.game.equipstreng.handler{

	import com.game.equipstreng.message.ResErrorInfoToClientMessage;
	import net.Handler;

	public class ResErrorInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResErrorInfoToClientMessage = ResErrorInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}