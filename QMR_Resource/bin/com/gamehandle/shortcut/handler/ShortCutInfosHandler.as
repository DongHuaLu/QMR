package com.game.shortcut.handler{

	import com.game.shortcut.message.ShortCutInfosMessage;
	import net.Handler;

	public class ShortCutInfosHandler extends Handler {
	
		public override function action(): void{
			var msg: ShortCutInfosMessage = ShortCutInfosMessage(this.message);
			//TODO 添加消息处理
		}
	}
}