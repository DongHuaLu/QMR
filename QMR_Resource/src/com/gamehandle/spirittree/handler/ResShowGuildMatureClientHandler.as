package com.game.spirittree.handler{

	import com.game.spirittree.message.ResShowGuildMatureClientMessage;
	import net.Handler;

	public class ResShowGuildMatureClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShowGuildMatureClientMessage = ResShowGuildMatureClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}