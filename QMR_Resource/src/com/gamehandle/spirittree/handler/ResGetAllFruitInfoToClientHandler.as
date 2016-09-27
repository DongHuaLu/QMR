package com.game.spirittree.handler{

	import com.game.spirittree.message.ResGetAllFruitInfoToClientMessage;
	import net.Handler;

	public class ResGetAllFruitInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetAllFruitInfoToClientMessage = ResGetAllFruitInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}