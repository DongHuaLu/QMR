package com.game.rank.handler{

	import com.game.rank.message.ResRankExpToClientMessage;
	import net.Handler;

	public class ResRankExpToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRankExpToClientMessage = ResRankExpToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}