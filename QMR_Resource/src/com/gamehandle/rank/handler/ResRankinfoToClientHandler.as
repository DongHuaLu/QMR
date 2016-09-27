package com.game.rank.handler{

	import com.game.rank.message.ResRankinfoToClientMessage;
	import net.Handler;

	public class ResRankinfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRankinfoToClientMessage = ResRankinfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}