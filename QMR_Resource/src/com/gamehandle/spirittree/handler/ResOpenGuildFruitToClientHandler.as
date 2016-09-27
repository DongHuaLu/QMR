package com.game.spirittree.handler{

	import com.game.spirittree.message.ResOpenGuildFruitToClientMessage;
	import net.Handler;

	public class ResOpenGuildFruitToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOpenGuildFruitToClientMessage = ResOpenGuildFruitToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}