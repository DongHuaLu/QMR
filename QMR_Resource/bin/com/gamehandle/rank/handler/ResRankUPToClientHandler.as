package com.game.rank.handler{

	import com.game.rank.message.ResRankUPToClientMessage;
	import net.Handler;

	public class ResRankUPToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRankUPToClientMessage = ResRankUPToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}