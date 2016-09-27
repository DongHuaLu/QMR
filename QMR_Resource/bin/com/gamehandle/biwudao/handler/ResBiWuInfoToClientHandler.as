package com.game.biwudao.handler{

	import com.game.biwudao.message.ResBiWuInfoToClientMessage;
	import net.Handler;

	public class ResBiWuInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBiWuInfoToClientMessage = ResBiWuInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}