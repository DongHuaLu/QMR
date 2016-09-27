package com.game.spirittree.handler{

	import com.game.spirittree.message.ResShowToRewardListToClientMessage;
	import net.Handler;

	public class ResShowToRewardListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShowToRewardListToClientMessage = ResShowToRewardListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}