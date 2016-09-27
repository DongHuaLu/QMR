package com.game.spirittree.handler{

	import com.game.spirittree.message.ResGuildFruitLogToClientMessage;
	import net.Handler;

	public class ResGuildFruitLogToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildFruitLogToClientMessage = ResGuildFruitLogToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}