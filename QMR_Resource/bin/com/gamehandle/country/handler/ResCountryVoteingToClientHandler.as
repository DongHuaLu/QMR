package com.game.country.handler{

	import com.game.country.message.ResCountryVoteingToClientMessage;
	import net.Handler;

	public class ResCountryVoteingToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryVoteingToClientMessage = ResCountryVoteingToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}