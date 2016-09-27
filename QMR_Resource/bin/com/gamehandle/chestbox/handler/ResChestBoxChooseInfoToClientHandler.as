package com.game.chestbox.handler{

	import com.game.chestbox.message.ResChestBoxChooseInfoToClientMessage;
	import net.Handler;

	public class ResChestBoxChooseInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChestBoxChooseInfoToClientMessage = ResChestBoxChooseInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}