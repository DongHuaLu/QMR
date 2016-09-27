package com.game.gift.handler{

	import com.game.gift.message.ResShuffleRaffleMessage;
	import net.Handler;

	public class ResShuffleRaffleHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShuffleRaffleMessage = ResShuffleRaffleMessage(this.message);
			//TODO 添加消息处理
		}
	}
}