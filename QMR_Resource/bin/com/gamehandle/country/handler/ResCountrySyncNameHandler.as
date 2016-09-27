package com.game.country.handler{

	import com.game.country.message.ResCountrySyncNameMessage;
	import net.Handler;

	public class ResCountrySyncNameHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountrySyncNameMessage = ResCountrySyncNameMessage(this.message);
			//TODO 添加消息处理
		}
	}
}