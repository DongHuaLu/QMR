package com.game.chestbox.handler{

	import com.game.chestbox.message.ResChestBoxInfoToClientMessage;
	import net.Handler;

	public class ResChestBoxInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChestBoxInfoToClientMessage = ResChestBoxInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}