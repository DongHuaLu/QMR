package com.game.chestbox.handler{

	import com.game.chestbox.message.ResChestBoxNewGridInfoToClientMessage;
	import net.Handler;

	public class ResChestBoxNewGridInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChestBoxNewGridInfoToClientMessage = ResChestBoxNewGridInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}