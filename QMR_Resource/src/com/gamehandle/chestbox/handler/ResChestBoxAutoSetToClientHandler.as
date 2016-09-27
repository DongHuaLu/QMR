package com.game.chestbox.handler{

	import com.game.chestbox.message.ResChestBoxAutoSetToClientMessage;
	import net.Handler;

	public class ResChestBoxAutoSetToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChestBoxAutoSetToClientMessage = ResChestBoxAutoSetToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}