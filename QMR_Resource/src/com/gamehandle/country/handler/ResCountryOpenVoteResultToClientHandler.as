package com.game.country.handler{

	import com.game.country.message.ResCountryOpenVoteResultToClientMessage;
	import net.Handler;

	public class ResCountryOpenVoteResultToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryOpenVoteResultToClientMessage = ResCountryOpenVoteResultToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}