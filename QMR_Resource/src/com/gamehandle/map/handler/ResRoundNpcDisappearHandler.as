package com.game.map.handler{

	import com.game.map.message.ResRoundNpcDisappearMessage;
	import net.Handler;

	public class ResRoundNpcDisappearHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundNpcDisappearMessage = ResRoundNpcDisappearMessage(this.message);
			//TODO 添加消息处理
		}
	}
}