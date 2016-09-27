package com.game.player.handler{

	import com.game.player.message.ResPlayerAttributeChangeMessage;
	import net.Handler;

	public class ResPlayerAttributeChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerAttributeChangeMessage = ResPlayerAttributeChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}